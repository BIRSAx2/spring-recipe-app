package dev.mouhieddine.recipeapp.controllers;

import dev.mouhieddine.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
  RecipeService recipeService;

  public IndexController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @RequestMapping({"", "/", "/index", "/index.html"})
  public String getIndexPage(Model model) {
    model.addAttribute("recipes", recipeService.getRecipes());
    return "index";
  }
}
