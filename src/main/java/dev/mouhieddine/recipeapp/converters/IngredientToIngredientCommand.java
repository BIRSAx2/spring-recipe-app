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
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
  private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

  public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
    this.uomConverter = unitOfMeasureToUnitOfMeasureCommand;
  }

  @Synchronized
  @Nullable
  @Override
  public IngredientCommand convert(Ingredient ingredient) {
    if (ingredient == null) return null;

    final IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setId(ingredient.getId());
    ingredientCommand.setDescription(ingredient.getDescription());
    ingredientCommand.setAmount(ingredient.getAmount());
    ingredientCommand.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));
    return ingredientCommand;
  }
}
