package dev.mouhieddine.recipeapp.controllers;

import dev.mouhieddine.recipeapp.commands.IngredientCommand;
import dev.mouhieddine.recipeapp.commands.RecipeCommand;
import dev.mouhieddine.recipeapp.services.IngredientService;
import dev.mouhieddine.recipeapp.services.RecipeService;
import dev.mouhieddine.recipeapp.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/12/2020, Saturday
 **/

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {
  @Mock
  IngredientService ingredientService;
  @Mock
  UnitOfMeasureService unitOfMeasureService;
  @Mock
  RecipeService recipeService;

  IngredientController controller;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {
    controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void testListIngredients() throws Exception {
    //given
    RecipeCommand recipeCommand = new RecipeCommand();
    when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

    //when
    mockMvc.perform(get("/recipe/1/ingredients"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/list"))
            .andExpect(model().attributeExists("recipe"));

    //then
    verify(recipeService, times(1)).findCommandById(anyLong());
  }

  @Test
  public void testShowIngredient() throws Exception {
    //given
    IngredientCommand ingredientCommand = new IngredientCommand();

    //when
    when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

    //then
    mockMvc.perform(get("/recipe/1/ingredient/2/show"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/show"))
            .andExpect(model().attributeExists("ingredient"));
  }


  @Test
  void testUpdateIngredientForm() throws Exception {
    // given
    IngredientCommand command = new IngredientCommand();
    // when
    when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);
    when(unitOfMeasureService.getUnitOfMeasures()).thenReturn(new HashSet<>());
    // then

    mockMvc.perform(get("/recipe/1/ingredient/2/update"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/ingredientForm"))
            .andExpect(model().attributeExists("ingredient"))
            .andExpect(model().attributeExists("uomList"));
  }

  @Test
  void testSaveOrUpdate() throws Exception {
    // given
    IngredientCommand ingredientCommand = IngredientCommand.builder().id(3L).recipeId(2L).build();
    // when
    when(ingredientService.saveIngredientCommand(any(IngredientCommand.class))).thenReturn(ingredientCommand);
    // then
    mockMvc.perform(post("/recipe/2/ingredient")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "")
            .param("description", "some string"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));
  }

  @Test
  void testNewIngredientForm() throws Exception {
    // given
    RecipeCommand recipeCommand = RecipeCommand.builder().id(1L).build();
    // when
    when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
    when(unitOfMeasureService.getUnitOfMeasures()).thenReturn(new HashSet<>());
    // then
    mockMvc.perform(get("/recipe/1/ingredient/new"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/ingredientForm"))
            .andExpect(model().attributeExists("ingredient"))
            .andExpect(model().attributeExists("uomList"));
    verify(recipeService, times(1)).findCommandById(anyLong());
    verify(unitOfMeasureService, times(1)).getUnitOfMeasures();
  }
  @Test
  void testDeleteIngredient() throws Exception {
    // given
    // when
    // then

    mockMvc.perform(get("/recipe/1/ingredient/1/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/1/ingredients"));

    verify(ingredientService,times(1)).deleteByRecipeIdAndIngredientId(anyLong(),anyLong());
  }
}