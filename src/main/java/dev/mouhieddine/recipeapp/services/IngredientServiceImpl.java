package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.commands.IngredientCommand;
import dev.mouhieddine.recipeapp.converters.IngredientToIngredientCommand;
import dev.mouhieddine.recipeapp.domain.Recipe;
import dev.mouhieddine.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : Mouhieddine.dev
 * @since : 12/12/2020, Saturday
 **/

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
  private final IngredientToIngredientCommand ingredientToIngredientCommand;
  private final RecipeRepository recipeRepository;

  public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
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
            .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

    if (ingredientCommandOptional.isEmpty()) {
      // todo impl error handling
      log.error("Ingredient id not found: " + ingredientId);
    }

    return ingredientCommandOptional.get();

  }
}