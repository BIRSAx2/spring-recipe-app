package dev.mouhieddine.recipeapp.converters;

import dev.mouhieddine.recipeapp.commands.RecipeCommand;
import dev.mouhieddine.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author : Mouhieddine.dev
 * @since : 12/11/2020, Friday
 **/
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
  private final CategoryCommandToCategory categoryConverter;
  private final IngredientCommandToIngredient ingredientConverter;
  private final NotesCommandToNotes notesConverter;

  public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter) {
    this.categoryConverter = categoryConverter;
    this.ingredientConverter = ingredientConverter;
    this.notesConverter = notesConverter;
  }

  @Synchronized
  @Nullable
  @Override
  public Recipe convert(RecipeCommand recipeCommand) {
    if (recipeCommand == null) return null;

    Recipe recipe = new Recipe();
    recipe.setId(recipeCommand.getId());
    recipe.setDescription(recipeCommand.getDescription());
    recipe.setPrepTime(recipeCommand.getPrepTime());
    recipe.setCookTime(recipeCommand.getCookTime());
    recipe.setServings(recipeCommand.getServings());
    recipe.setSource(recipeCommand.getSource());
    recipe.setUrl(recipeCommand.getUrl());
    recipe.setDirections(recipeCommand.getDirections());
    recipe.setDifficulty(recipeCommand.getDifficulty());
    recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));

    if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0)
      recipeCommand.getCategories().forEach(el -> recipe.getCategories().add(categoryConverter.convert(el)));

    if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0)
      recipeCommand.getIngredients().forEach(el -> recipe.getIngredients().add(ingredientConverter.convert(el)));
    return recipe;
  }
}
