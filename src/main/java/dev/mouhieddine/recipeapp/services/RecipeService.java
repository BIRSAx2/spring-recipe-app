package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.commands.RecipeCommand;
import dev.mouhieddine.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
  Set<Recipe> getRecipes();
  Recipe findRecipeById(Long id);
  RecipeCommand saveRecipeCommand (RecipeCommand recipeCommand);
  RecipeCommand findCommandById(Long id);
  void deleteById(Long id);
}
