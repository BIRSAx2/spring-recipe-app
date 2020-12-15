package dev.mouhieddine.recipeapp.controllers;

import dev.mouhieddine.recipeapp.commands.IngredientCommand;
import dev.mouhieddine.recipeapp.commands.RecipeCommand;
import dev.mouhieddine.recipeapp.commands.UnitOfMeasureCommand;
import dev.mouhieddine.recipeapp.services.IngredientService;
import dev.mouhieddine.recipeapp.services.RecipeService;
import dev.mouhieddine.recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/12/2020, Saturday
 **/
@Slf4j
@Controller
public class IngredientController {
  RecipeService recipeService;
  IngredientService ingredientService;
  UnitOfMeasureService unitOfMeasureService;

  public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
    this.recipeService = recipeService;
    this.ingredientService = ingredientService;
    this.unitOfMeasureService = unitOfMeasureService;
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

  @GetMapping
  @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
  public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
    return "redirect:/";
  }

  @GetMapping
  @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
  public String updateIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
    model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
    model.addAttribute("uomList", unitOfMeasureService.getUnitOfMeasures());
    return "recipe/ingredient/ingredientForm";
  }

  @GetMapping
  @RequestMapping("/recipe/{recipeId}/ingredient/new")
  public String newIngredient(@PathVariable String recipeId, Model model) {
//    RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId)); //todo throw exception if recipeCommand is null

//    IngredientCommand ingredientCommand = IngredientCommand.builder().recipeId(Long.valueOf(recipeId)).unitOfMeasure(new UnitOfMeasureCommand()).build();


    //make sure we have a good id value
    RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
    //todo raise exception if null

    //need to return back parent id for hidden form property
    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setRecipeId(Long.valueOf(recipeId));
    model.addAttribute("ingredient", ingredientCommand);

    //init uom
    ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

    model.addAttribute("uomList", unitOfMeasureService.getUnitOfMeasures());
    return "recipe/ingredient/ingredientForm";
  }

  @PostMapping
  @RequestMapping("/recipe/{recipeId}/ingredient")
  public String saveOrUpdate(@ModelAttribute IngredientCommand command, Model model) {
    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
    log.debug("received receipe id :" + command.getRecipeId());
    log.debug("saved receipe id :" + savedCommand.getRecipeId());
    log.debug("saved ingredient id:" + savedCommand.getId());
    return String.format("redirect:/recipe/%d/ingredient/%d/show", savedCommand.getRecipeId(), savedCommand.getId());

  }
}
