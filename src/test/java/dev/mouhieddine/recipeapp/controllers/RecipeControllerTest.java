package dev.mouhieddine.recipeapp.controllers;

import dev.mouhieddine.recipeapp.domain.Recipe;
import dev.mouhieddine.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author : Mouhieddine.dev
 * @since : 12/9/2020, Wednesday
 **/

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {
  private final Long RECIPE_ID = 1L;
  @Mock
  RecipeService recipeService;
  @InjectMocks
  RecipeController recipeController;
  MockMvc mockMvc;
  private Recipe recipe;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    recipe = new Recipe();
    recipe.setId(RECIPE_ID);

  }

  @Test
  void showById() throws Exception {
    when(recipeService.findRecipeById(anyLong())).thenReturn(null);
    mockMvc.perform(get("/recipe/show/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/show"));
  }
}