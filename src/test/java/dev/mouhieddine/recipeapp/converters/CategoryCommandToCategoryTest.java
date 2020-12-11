package dev.mouhieddine.recipeapp.converters;

import dev.mouhieddine.recipeapp.commands.CategoryCommand;
import dev.mouhieddine.recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/11/2020, Friday
 **/
class CategoryCommandToCategoryTest {
  public static final Long ID_VALUE = 1L;
  public static final String DESCRIPTION = "description";
  CategoryCommandToCategory converter;

  @BeforeEach
  void setUp() {
    converter = new CategoryCommandToCategory();
  }

  @Test
  void testNullObject() throws Exception {
    assertNull(converter.convert(null));
  }

  @Test
  void testEmptyObject() {
    assertNotNull(converter.convert(new CategoryCommand()));
  }

  @Test
  void convert() {
    // given
    CategoryCommand categoryCommand = new CategoryCommand();
    categoryCommand.setId(ID_VALUE);
    categoryCommand.setDescription(DESCRIPTION);
    // when
    Category category = converter.convert(categoryCommand);
    // then
    assertNotNull(category);
    assertEquals(ID_VALUE,category.getId());
    assertEquals(DESCRIPTION,category.getDescription());
  }
}