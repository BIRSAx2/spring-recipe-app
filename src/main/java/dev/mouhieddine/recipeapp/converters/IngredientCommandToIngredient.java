package dev.mouhieddine.recipeapp.converters;

import dev.mouhieddine.recipeapp.commands.IngredientCommand;
import dev.mouhieddine.recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author : Mouhieddine.dev
 * @since : 12/11/2020, Friday
 **/
@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
  private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

  public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
    this.uomConverter = unitOfMeasureCommandToUnitOfMeasure;
  }

  @Synchronized
  @Nullable
  @Override
  public Ingredient convert(IngredientCommand ingredientCommand) {
    if (ingredientCommand == null) return null;

    final Ingredient ingredient = new Ingredient();
    ingredient.setId(ingredientCommand.getId());
    ingredient.setUnitOfMeasure(uomConverter.convert(ingredientCommand.getUnitOfMeasure()));
    ingredient.setAmount(ingredientCommand.getAmount());
    ingredient.setDescription(ingredientCommand.getDescription());
    return ingredient;
  }
}
