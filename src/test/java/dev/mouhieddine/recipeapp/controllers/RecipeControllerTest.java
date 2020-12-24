package dev.mouhieddine.recipeapp.controllers;

import dev.mouhieddine.recipeapp.commands.RecipeCommand;
import dev.mouhieddine.recipeapp.domain.Recipe;
import dev.mouhieddine.recipeapp.exceptions.NotFoundException;
import dev.mouhieddine.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
    mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
            .setControllerAdvice(new ExceptionHandlerController())
            .build();
    recipe = new Recipe();
    recipe.setId(RECIPE_ID);

  }

  @Test
  void showById() throws Exception {
    when(recipeService.findRecipeById(anyLong())).thenReturn(Recipe.builder().id(RECIPE_ID).build());
    mockMvc.perform(get("/recipe/1/show"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("recipe"))
            .andExpect(view().name("recipe/show"));
  }

  @Test
  void showByIdRecipeNotFound() throws Exception {
    when(recipeService.findRecipeById(anyLong())).thenThrow(NotFoundException.class);
    mockMvc.perform(get("/recipe/1/show"))
            .andExpect(status().isNotFound())
            .andExpect(view().name("404error"));
  }

  @Test
  void showByIdMalformedId() throws Exception {
//    when(recipeService.findRecipeById(anyLong())).thenThrow(NotFoundException.class);
    mockMvc.perform(get("/recipe/a/show"))
            .andExpect(status().isBadRequest())
            .andExpect(view().name("400error"));
  }

  @Test
  public void testPostNewRecipeForm() throws Exception {
    RecipeCommand command = new RecipeCommand();
    command.setId(2L);

    when(recipeService.saveRecipeCommand(any())).thenReturn(command);

    mockMvc.perform(post("/recipe")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "")
            .param("description", "some string"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/2/show"));
  }

  @Test
  public void testGetUpdateView() throws Exception {
    RecipeCommand command = new RecipeCommand();
    command.setId(2L);

    when(recipeService.findCommandById(anyLong())).thenReturn(command);

    mockMvc.perform(get("/recipe/1/update"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/recipeForm"))
            .andExpect(model().attributeExists("recipe"));
  }

  @Test
  void deleteById() throws Exception {
    mockMvc.perform(get("/recipe/1/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"));
    verify(recipeService, times(1)).deleteById(anyLong());
  }
}