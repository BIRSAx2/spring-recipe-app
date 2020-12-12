package dev.mouhieddine.recipeapp.controllers;

import dev.mouhieddine.recipeapp.commands.RecipeCommand;
import dev.mouhieddine.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping
  @RequestMapping("/recipe/{id}/show")
  public String showById(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));
    return "recipe/show";
  }

  @GetMapping
  @RequestMapping("/recipe/new")
  public String newRecipe(Model model) {
    model.addAttribute("recipe", new RecipeCommand());
    return "recipe/recipeForm";
  }

  // @ModelAttribute tells Spring to bind form posts to recipeCommand Object
  @PostMapping // this tells Spring to invoke this method when there is a post request to /recipe
  @RequestMapping("/recipe")
  public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
    RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
    return "redirect:/recipe/" + savedCommand.getId() + "/show";
  }

  @GetMapping
  @RequestMapping("recipe/{id}/update")
  public String updateRecipe(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
    return "recipe/recipeForm";
  }


  @GetMapping
  @RequestMapping("/recipe/{id}/delete")
  public String delete(@PathVariable String id, Model model) {
    recipeService.deleteById((Long.valueOf(id)));
    return "redirect:/";
  }
}
