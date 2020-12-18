package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.commands.IngredientCommand;
import dev.mouhieddine.recipeapp.converters.IngredientCommandToIngredient;
import dev.mouhieddine.recipeapp.converters.IngredientToIngredientCommand;
import dev.mouhieddine.recipeapp.domain.Ingredient;
import dev.mouhieddine.recipeapp.domain.Recipe;
import dev.mouhieddine.recipeapp.repositories.RecipeRepository;
import dev.mouhieddine.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author : Mouhieddine.dev
 * @since : 12/12/2020, Saturday
 **/

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
  private final IngredientToIngredientCommand ingredientToIngredientCommand;
  private final IngredientCommandToIngredient ingredientCommandToIngredient;
  private final UnitOfMeasureRepository unitOfMeasureRepository;
  private final RecipeRepository recipeRepository;

  public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
    this.recipeRepository = recipeRepository;
  }

  @Override
  public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

    if (recipeOptional.isEmpty()) {
      // todo impl error handling
      log.error("recipe id not found. Id: " + recipeId);
    }

    Recipe recipe = recipeOptional.get();

    Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
            .filter(ingredient -> ingredient.getId().equals(ingredientId))
            .map(ingredientToIngredientCommand::convert).findFirst();

    if (ingredientCommandOptional.isEmpty()) {
      // todo impl error handling
      log.error("Ingredient id not found: " + ingredientId);
    }

    return ingredientCommandOptional.get();
  }

  @Transactional
  public IngredientCommand saveIngredientCommand(IngredientCommand command) {
    if (command == null) throw new RuntimeException("Ingredient Command object must not be null");
    Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
    if (recipeOptional.isEmpty()) {
      log.error("Recipe not found for id:" + command.getId());
      return new IngredientCommand();
    }

    Recipe recipe = recipeOptional.get();
    Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream().filter(el -> el.getId().equals(command.getId())).findFirst();
    if (ingredientOptional.isEmpty()) {
      // if the ingredient was not found create one
      Ingredient ingredient = ingredientCommandToIngredient.convert(command);
      ingredient.setRecipe(recipe);
      recipe.addIngredient(ingredient);
    } else {
      // otherwise modify the found one
      Ingredient ingredientFound = ingredientOptional.get();
      ingredientFound.setDescription(command.getDescription());
      ingredientFound.setAmount(command.getAmount());
      ingredientFound.setUnitOfMeasure(unitOfMeasureRepository.findById(command.getUnitOfMeasure().getId()).orElseThrow(() -> new RuntimeException("UnitOfMeasure must not be null")));
    }

    Recipe savedRecipe = recipeRepository.save(recipe);
    // trying to find the new or modified ingredient
    Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream().filter(el -> el.getId().equals(command.getId())).findFirst();
    // empty-> the ingrediend was a new ingredient
    if (savedIngredientOptional.isEmpty()) {
      // not safe but best guess, may not work because description, amount and unitOfMeasure are not unique
      savedIngredientOptional = savedRecipe.getIngredients().stream()
              .filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))
              .filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
              .filter(ingredient -> ingredient.getUnitOfMeasure().getId().equals(command.getUnitOfMeasure().getId()))
              .findFirst();
    }
    return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
  }

  @Override
  @Transactional
  public void deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
    log.debug("Deleting ingredient " + ingredientId + " from recipe " + recipeId);
    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
    if (recipeOptional.isEmpty()) {
      // todo impl error handling
      log.error("recipe id not found. Id: " + recipeId);
      throw new RuntimeException("Recipe not found");
    }
    Recipe recipe = recipeOptional.get();
    Optional<Ingredient> ingredientOptional = recipeOptional.get().getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();
    if (ingredientOptional.isPresent()) {
      log.debug("Found ingredient " + ingredientId + " in recipe " + recipeId);
      Ingredient ingredientToDelete = ingredientOptional.get();
      ingredientToDelete.setRecipe(null);
      recipe.getIngredients().remove(ingredientOptional.get());
      recipeRepository.save(recipe);
      log.debug("Ingredient " + ingredientId + " deleted");

    }
  }

}