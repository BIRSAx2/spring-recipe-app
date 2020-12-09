package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.domain.Recipe;
import dev.mouhieddine.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
  private final RecipeRepository recipeRepository;

  public RecipeServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
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
    if(recipeOptional.isEmpty()){
      throw new RuntimeException("Recipe Not Found");
    }
    return recipeOptional.get();
  }

}
