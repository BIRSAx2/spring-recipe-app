package dev.mouhieddine.recipeapp.controllers;

import dev.mouhieddine.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Mouhieddine.dev
 * @since : 12/9/2020, Wednesday
 **/
@Controller
public class RecipeController {
  private final RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @RequestMapping("/recipe/show/{id}")
  public String showById(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));
    return "recipe/show";
  }
}
