package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.commands.IngredientCommand;
import dev.mouhieddine.recipeapp.converters.IngredientCommandToIngredient;
import dev.mouhieddine.recipeapp.converters.IngredientToIngredientCommand;
import dev.mouhieddine.recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import dev.mouhieddine.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import dev.mouhieddine.recipeapp.domain.Ingredient;
import dev.mouhieddine.recipeapp.domain.Recipe;
import dev.mouhieddine.recipeapp.repositories.RecipeRepository;
import dev.mouhieddine.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/13/2020, Sunday
 **/

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

  @Mock
  RecipeRepository recipeRepository;
  @Mock
  UnitOfMeasureRepository unitOfMeasureRepository;

  IngredientToIngredientCommand ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
  IngredientCommandToIngredient ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());


  IngredientServiceImpl service;

  @BeforeEach
  void setUp() {
    service = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient, unitOfMeasureRepository, recipeRepository);
  }

  @Test
  void findByRecipeIdAndIngredientIdHappyPath() {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    Ingredient ingredient1 = new Ingredient();
    ingredient1.setId(1L);
    Ingredient ingredient2 = new Ingredient();
    ingredient1.setId(2L);
    Ingredient ingredient3 = new Ingredient();
    ingredient1.setId(3L);
  }

  @Test
  void saveIngredientCommand() {
    // given
    IngredientCommand command = new IngredientCommand();
    command.setId(3L);
    command.setRecipeId(2L);

    Optional<Recipe> recipeOptional = Optional.of(new Recipe());

    Recipe savedRecipe = new Recipe();
    savedRecipe.addIngredient(Ingredient.builder().id(3L).build());

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
    when(recipeRepository.save(any(Recipe.class))).thenReturn(savedRecipe);
    // when
    IngredientCommand savedCommand = service.saveIngredientCommand(command);
    // then

    assertEquals(Long.valueOf(3L), savedCommand.getId());
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, times(1)).save(any(Recipe.class));
  }
}