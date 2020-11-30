package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
  Set<Recipe> getRecipes();
}
