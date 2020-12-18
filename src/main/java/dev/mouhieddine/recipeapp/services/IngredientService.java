package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.commands.IngredientCommand;

/**
 * @author : Mouhieddine.dev
 * @since : 12/12/2020, Saturday
 **/
public interface IngredientService {
  IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
  IngredientCommand saveIngredientCommand(IngredientCommand command);
  void deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
