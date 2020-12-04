package dev.mouhieddine.recipeapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Mouhieddine.dev
 * @created : 12/4/2020, Friday
 **/
class CategoryTest {

  Category category;

  @BeforeEach
  public void setUp() {
    category = new Category();
  }

  @Test
  void getId() {
    Long idValue = 4L;

    category.setId(idValue);

    assertEquals(idValue, category.getId());
  }

  @Test
  void getDescription() {
  }

  @Test
  void getRecipes() {
  }
}