package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.domain.Recipe;
import dev.mouhieddine.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author : Mouhieddine.dev
 * @created : 12/4/2020, Friday
 **/
class RecipeServiceImplTest {
  private RecipeServiceImpl recipeService;

  @Mock
  private RecipeRepository recipeRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    recipeService = new RecipeServiceImpl(recipeRepository);
  }

  @Test
  void getRecipes() {
    Recipe recipe = new Recipe();
    HashSet<Recipe> recipesData = new HashSet<>();
    recipesData.add(recipe);

    when(recipeRepository.findAll()).thenReturn(recipesData);

    Set<Recipe> recipes = recipeService.getRecipes();
    assertEquals(recipes.size(),1);

    verify(recipeRepository,times(1)).findAll();
  }
}