package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.domain.Recipe;
import dev.mouhieddine.recipeapp.exceptions.NotFoundException;
import dev.mouhieddine.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/4/2020, Friday
 **/
@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {


  @Mock
  private RecipeRepository recipeRepository;
  @InjectMocks
  private RecipeServiceImpl recipeService;

  private Long RECIPE_ID = 1L;

  @BeforeEach
  void setUp() {
//    recipeService = new RecipeServiceImpl(recipeRepository);
  }

  @Test
  void getRecipes() {
    Recipe recipe = new Recipe();
    HashSet<Recipe> recipesData = new HashSet<>();
    recipesData.add(recipe);

    when(recipeRepository.findAll()).thenReturn(recipesData);

    Set<Recipe> recipes = recipeService.getRecipes();
    assertEquals(recipes.size(), 1);

    verify(recipeRepository, times(1)).findAll();
  }

  @Test
  void findRecipeById() {
    Recipe recipe = new Recipe();
    recipe.setId(RECIPE_ID);
    when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

    Recipe returnRecipe = recipeService.findRecipeById(RECIPE_ID);
    assertNotNull(returnRecipe, "Null Recipe returned");
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, never()).findAll();

  }

  @Test
  public void testDeleteById() throws Exception {

    //given
    Long idToDelete = 2L;
    //when
    recipeService.deleteById(idToDelete);
    //no 'when', since method has void return type
    //then
    verify(recipeRepository, times(1)).deleteById(anyLong());
  }

  @Test
  void findRecipeByIdNotFound() throws Exception {

    Optional<Recipe> recipeOptional = Optional.empty();
    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
    assertThrows(NotFoundException.class, () -> {
      Recipe recipeReturned = recipeService.findRecipeById(1L);
    });
  }
}