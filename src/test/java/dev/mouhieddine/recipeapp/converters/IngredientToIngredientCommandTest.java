package dev.mouhieddine.recipeapp.converters;

import dev.mouhieddine.recipeapp.commands.IngredientCommand;
import dev.mouhieddine.recipeapp.domain.Ingredient;
import dev.mouhieddine.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/11/2020, Friday
 **/
class IngredientToIngredientCommandTest {
  public static final BigDecimal AMOUNT = new BigDecimal("1");
  public static final String DESCRIPTION = "Cheeseburger";
  public static final Long ID_VALUE = 1L;
  public static final Long UOM_ID = 2L;

  private IngredientToIngredientCommand converter;

  @BeforeEach
  void setUp() {
    converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
  }

  @Test
  void testNullObject() {
    assertNull(converter.convert(null));
  }

  @Test
  void testEmptyObject() {
    assertNotNull(converter.convert(new Ingredient()));
  }

  @Test
  void convert() {
    // given
    Ingredient ingredient = new Ingredient();
    ingredient.setId(ID_VALUE);
    ingredient.setDescription(DESCRIPTION);
    ingredient.setAmount(AMOUNT);
    UnitOfMeasure uom = new UnitOfMeasure();
    uom.setId(UOM_ID);
    ingredient.setUnitOfMeasure(uom);

    // when
    IngredientCommand ingredientCommand = converter.convert(ingredient);
    // then

    assertNotNull(ingredientCommand);
    assertNotNull(ingredientCommand.getUnitOfMeasure());
    assertEquals(ID_VALUE, ingredientCommand.getId());
    assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    assertEquals(AMOUNT, ingredientCommand.getAmount());
    assertEquals(UOM_ID, ingredientCommand.getUnitOfMeasure().getId());
  }
}