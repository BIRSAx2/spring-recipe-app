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
class CategoryToCategoryCommandTest {
  public static final Long ID_VALUE = 1L;
  public static final String DESCRIPTION = "description";
  CategoryToCategoryCommand converter;

  @BeforeEach
  void setUp() {
    converter = new CategoryToCategoryCommand();
  }

  @Test
  void testNullObject() throws Exception {
    assertNull(converter.convert(null));
  }

  @Test
  void testEmptyObject() {
    assertNotNull(converter.convert(new Category()));
  }

  @Test
  void convert() {
    // given
    Category category = new Category();
    category.setId(ID_VALUE);
    category.setDescription(DESCRIPTION);
    // when
    CategoryCommand categoryCommand = converter.convert(category);
    // then
    assertNotNull(categoryCommand);
    assertEquals(ID_VALUE, categoryCommand.getId());
    assertEquals(DESCRIPTION, categoryCommand.getDescription());
  }
}