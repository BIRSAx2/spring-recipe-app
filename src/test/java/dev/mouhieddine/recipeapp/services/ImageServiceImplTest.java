package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.domain.Recipe;
import dev.mouhieddine.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/20/2020, Sunday
 **/
@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {
  @Mock
  RecipeRepository recipeRepository;

  ImageService imageService;


  @BeforeEach
  void setUp() {
    imageService = new ImageServiceImpl(recipeRepository);
  }

  @Test
  void saveImageFile() throws IOException {
    // given
    Long id = 1L;
    MultipartFile multipartFile = new MockMultipartFile("imageFile",
            "testing.txt",
            "text/plain",
            "Mouhieddine.dev".getBytes());
    Optional<Recipe> recipeOptional = Optional.of(Recipe.builder().id(id).build());
    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);
    // when
    imageService.saveImageFile(id, multipartFile);
    // then
    verify(recipeRepository, times(1)).save(argumentCaptor.capture());
    Recipe savedRecipe = argumentCaptor.getValue();
    assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
  }
}