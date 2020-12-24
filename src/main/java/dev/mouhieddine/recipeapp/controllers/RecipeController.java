package dev.mouhieddine.recipeapp.controllers;

import dev.mouhieddine.recipeapp.commands.RecipeCommand;
import dev.mouhieddine.recipeapp.exceptions.NotFoundException;
import dev.mouhieddine.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : Mouhieddine.dev
 * @since : 12/9/2020, Wednesday
 **/
@Controller
@Slf4j
public class RecipeController {
  private final RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping({"/recipe/{id}/show", "/recipe/{id}"})
  public String showById(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));
    return "recipe/show";
  }

  @GetMapping("/recipe/new")
  public String newRecipe(Model model) {
    model.addAttribute("recipe", new RecipeCommand());
    return "recipe/recipeForm";
  }

  // @ModelAttribute tells Spring to bind form posts to recipeCommand Object
  // this tells Spring to invoke this method when there is a post request to /recipe
  @PostMapping("/recipe")
  public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
    RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
    return "redirect:/recipe/" + savedCommand.getId() + "/show";
  }

  @GetMapping("recipe/{id}/update")
  public String updateRecipe(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
    return "recipe/recipeForm";
  }


  @GetMapping("/recipe/{id}/delete")
  public String delete(@PathVariable String id, Model model) {
    recipeService.deleteById((Long.valueOf(id)));
    return "redirect:/";
  }

  //  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public ModelAndView handleNotFoundException() {
    log.error("Handling not found exception");
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("404error");
    modelAndView.setStatus(HttpStatus.NOT_FOUND);
    return modelAndView;
  }

  @ExceptionHandler(NumberFormatException.class)
  public ModelAndView handleNumberFormatException(Exception exception) {
    log.error("Handling not number error exception");
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("400error");
    modelAndView.addObject("exception",exception);
    modelAndView.setStatus(HttpStatus.BAD_REQUEST);
    return modelAndView;
  }
}
