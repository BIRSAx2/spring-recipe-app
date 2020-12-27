package dev.mouhieddine.recipeapp.controllers;

import dev.mouhieddine.recipeapp.commands.RecipeCommand;
import dev.mouhieddine.recipeapp.exceptions.NotFoundException;
import dev.mouhieddine.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author : Mouhieddine.dev
 * @since : 12/9/2020, Wednesday
 **/
@Controller
@Slf4j
public class RecipeController {
  private final String VIEW_CREATE_OR_UPDATE_RECIPE_FORM = "recipe/recipeForm";
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
    return VIEW_CREATE_OR_UPDATE_RECIPE_FORM;
  }

  // @ModelAttribute tells Spring to bind form posts to recipeCommand Object
  // this tells Spring to invoke this method when there is a post request to /recipe
  @PostMapping("/recipe")
  public String saveOrUpdate(@Valid @ModelAttribute RecipeCommand recipeCommand, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {

      bindingResult.getAllErrors().forEach(objectError -> {
        log.debug(objectError.toString());
      });
      model.addAttribute("recipe", recipeService.findCommandById(recipeCommand.getId()));

      return VIEW_CREATE_OR_UPDATE_RECIPE_FORM;
    }
    RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
    return "redirect:/recipe/" + savedCommand.getId() + "/show";
  }

  @GetMapping("recipe/{id}/update")
  public String updateRecipe(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
    return VIEW_CREATE_OR_UPDATE_RECIPE_FORM;
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


}
