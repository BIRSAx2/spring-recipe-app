package dev.mouhieddine.recipeapp.controllers;

import dev.mouhieddine.recipeapp.services.IngredientService;
import dev.mouhieddine.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Mouhieddine.dev
 * @since : 12/12/2020, Saturday
 **/
@Controller
public class IngredientController {
  RecipeService recipeService;
  IngredientService ingredientService;

  public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
    this.recipeService = recipeService;
    this.ingredientService = ingredientService;
  }

  @GetMapping
  @RequestMapping("/recipe/{recipeId}/ingredients")
  public String listIngredient(@PathVariable String recipeId, Model model) {
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
    return "recipe/ingredient/list";
  }

  @GetMapping
  @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
  public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
    model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
    return "recipe/ingredient/show";
  }
}