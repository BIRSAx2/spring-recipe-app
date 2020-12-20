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
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
  private final CategoryToCategoryCommand categoryConverter;
  private final IngredientToIngredientCommand ingredientConverter;
  private final NotesToNotesCommand notesConverter;

  public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter) {
    this.categoryConverter = categoryConverter;
    this.ingredientConverter = ingredientConverter;
    this.notesConverter = notesConverter;
  }

  @Synchronized
  @Nullable
  @Override
  public RecipeCommand convert(Recipe recipe) {
    if (recipe == null) return null;
    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId(recipe.getId());
    recipeCommand.setDescription(recipe.getDescription());
    recipeCommand.setPrepTime(recipe.getPrepTime());
    recipeCommand.setCookTime(recipe.getCookTime());
    recipeCommand.setServings(recipe.getServings());
    recipeCommand.setSource(recipe.getSource());
    recipeCommand.setUrl(recipe.getUrl());
    recipeCommand.setDirections(recipe.getDirections());
    recipeCommand.setDifficulty(recipe.getDifficulty());
    recipeCommand.setImage(recipe.getImage());
    recipeCommand.setNotes(notesConverter.convert(recipe.getNotes()));
    if (recipe.getCategories() != null && recipe.getCategories().size() > 0)
      recipe.getCategories().forEach(el -> recipeCommand.getCategories().add(categoryConverter.convert(el)));

    if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0)
      recipe.getIngredients().forEach(el -> recipeCommand.getIngredients().add(ingredientConverter.convert(el)));
    return recipeCommand;
  }
}
