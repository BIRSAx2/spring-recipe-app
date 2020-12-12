package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.commands.RecipeCommand;
import dev.mouhieddine.recipeapp.converters.RecipeCommandToRecipe;
import dev.mouhieddine.recipeapp.converters.RecipeToRecipeCommand;
import dev.mouhieddine.recipeapp.domain.Recipe;
import dev.mouhieddine.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
  private final RecipeRepository recipeRepository;
  private final RecipeCommandToRecipe recipeCommandToRecipe;
  private final RecipeToRecipeCommand recipeToRecipeCommand;

  public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
    this.recipeRepository = recipeRepository;
    this.recipeCommandToRecipe = recipeCommandToRecipe;
    this.recipeToRecipeCommand = recipeToRecipeCommand;
  }

  @Override
  public Set<Recipe> getRecipes() {
    Set<Recipe> recipes = new HashSet<>();
    recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
    return recipes;
  }

  @Override
  public Recipe findRecipeById(Long id) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(id);
    if (recipeOptional.isEmpty()) {
      throw new RuntimeException("Recipe Not Found");
    }
    return recipeOptional.get();
  }

  @Override
  @Transactional
  public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
    Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
    Recipe savedRecipe = recipeRepository.save(detachedRecipe);
    return recipeToRecipeCommand.convert(savedRecipe);
  }

  @Override
  @Transactional
  public RecipeCommand findCommandById(Long id) {
    return recipeToRecipeCommand.convert(findRecipeById(id));
  }

  @Override
  public void deleteById(Long id) {
    recipeRepository.deleteById(id);
  }

}
