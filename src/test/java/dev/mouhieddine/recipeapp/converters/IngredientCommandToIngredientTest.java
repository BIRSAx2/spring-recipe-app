package dev.mouhieddine.recipeapp.converters;

import dev.mouhieddine.recipeapp.commands.IngredientCommand;
import dev.mouhieddine.recipeapp.commands.UnitOfMeasureCommand;
import dev.mouhieddine.recipeapp.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/11/2020, Friday
 **/
class IngredientCommandToIngredientTest {

  public static final BigDecimal AMOUNT = new BigDecimal("1");
  public static final String DESCRIPTION = "Cheeseburger";
  public static final Long ID_VALUE = 1L;
  public static final Long UOM_ID = 2L;

  private IngredientCommandToIngredient converter;

  @BeforeEach
  void setUp() {
    converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
  }

  @Test
  void testNullObject() {
    assertNull(converter.convert(null));
  }

  @Test
  void testEmptyObject() {
    assertNotNull(converter.convert(new IngredientCommand()));
  }

  @Test
  void convert() {
    // given
    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setId(ID_VALUE);
    ingredientCommand.setDescription(DESCRIPTION);
    ingredientCommand.setAmount(AMOUNT);
    UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
    uom.setId(UOM_ID);
    ingredientCommand.setUnitOfMeasure(uom);
    // when
    Ingredient ingredient = converter.convert(ingredientCommand);
    // then
    assertNotNull(ingredient);
    assertNotNull(ingredient.getUnitOfMeasure());
    assertEquals(ID_VALUE, ingredient.getId());
    assertEquals(DESCRIPTION, ingredient.getDescription());
    assertEquals(AMOUNT, ingredient.getAmount());
    assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
  }

  @Test
  void convertWithNullUnitOfMeasure() {
    // given
    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setId(ID_VALUE);
    ingredientCommand.setDescription(DESCRIPTION);
    ingredientCommand.setAmount(AMOUNT);
    // when
    Ingredient ingredient = converter.convert(ingredientCommand);
    // then
    assertNotNull(ingredient);
    assertNull(ingredient.getUnitOfMeasure());
    assertEquals(ID_VALUE, ingredient.getId());
    assertEquals(DESCRIPTION, ingredient.getDescription());
    assertEquals(AMOUNT, ingredient.getAmount());
  }
}