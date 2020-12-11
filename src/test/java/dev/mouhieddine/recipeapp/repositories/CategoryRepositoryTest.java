package dev.mouhieddine.recipeapp.repositories;

import dev.mouhieddine.recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/10/2020, Thursday
 **/

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class CategoryRepositoryTest {

  @Autowired
  CategoryRepository categoryRepository;
  final String AMERICAN = "American";

  @BeforeEach
  void setUp() {
  }

  @Test
  void findByDescription() {
    Optional<Category> optionalCategory = categoryRepository.findByDescription(AMERICAN);
    assertEquals(AMERICAN,optionalCategory.get().getDescription());
  }
}