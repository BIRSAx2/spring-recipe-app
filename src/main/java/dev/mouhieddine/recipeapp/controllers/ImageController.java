package dev.mouhieddine.recipeapp.controllers;

import dev.mouhieddine.recipeapp.commands.RecipeCommand;
import dev.mouhieddine.recipeapp.services.ImageService;
import dev.mouhieddine.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author : Mouhieddine.dev
 * @since : 12/20/2020, Sunday
 **/
@Controller
@Slf4j
public class ImageController {
  private final ImageService imageService;
  private final RecipeService recipeService;

  public ImageController(ImageService imageService, RecipeService recipeService) {
    this.imageService = imageService;
    this.recipeService = recipeService;
  }

  @GetMapping("/recipe/{id}/image")
  public String getImageForm(@PathVariable String id, Model model) {
    Optional<RecipeCommand> recipeCommandOptional = Optional.ofNullable(recipeService.findCommandById(Long.valueOf(id)));
    if (recipeCommandOptional.isEmpty()) throw new RuntimeException("Recipe not found");
    model.addAttribute("recipe", recipeCommandOptional.get());
    return "recipe/imageUploadForm";
  }

  @PostMapping("recipe/{id}/image")
  public String handleImagePost(@PathVariable String id, @RequestParam("imageFile") MultipartFile imageFile) {
    imageService.saveImageFile(Long.valueOf(id),imageFile);
    return "redirect:/recipe/" + id + "/show";
  }
}
