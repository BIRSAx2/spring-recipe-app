package dev.mouhieddine.recipeapp.converters;

import dev.mouhieddine.recipeapp.commands.UnitOfMeasureCommand;
import dev.mouhieddine.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/11/2020, Friday
 **/
class UnitOfMeasureToUnitOfMeasureCommandTest {
  public static final String DESCRIPTION = "description";
  public static final Long LONG_VALUE = 1L;

  UnitOfMeasureToUnitOfMeasureCommand converter;

  @BeforeEach
  public void setUp() throws Exception {
    converter = new UnitOfMeasureToUnitOfMeasureCommand();

  }

  @Test
  public void testNullParameter() throws Exception {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() throws Exception {
    assertNotNull(converter.convert(new UnitOfMeasure()));
  }

  @Test
  public void convert() throws Exception {
    //given
    UnitOfMeasure uom = new UnitOfMeasure();
    uom.setId(LONG_VALUE);
    uom.setDescription(DESCRIPTION);

    //when
    UnitOfMeasureCommand uomCommand = converter.convert(uom);

    //then
    assertNotNull(uomCommand);
    assertEquals(LONG_VALUE, uomCommand.getId());
    assertEquals(DESCRIPTION, uomCommand.getDescription());

  }
}