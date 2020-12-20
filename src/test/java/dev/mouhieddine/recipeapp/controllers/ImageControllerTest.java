package dev.mouhieddine.recipeapp.controllers;

import dev.mouhieddine.recipeapp.commands.RecipeCommand;
import dev.mouhieddine.recipeapp.services.ImageService;
import dev.mouhieddine.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/20/2020, Sunday
 **/
@ExtendWith(MockitoExtension.class)
class ImageControllerTest {
  @Mock
  ImageService imageService;
  @Mock
  RecipeService recipeService;
  ImageController controller;
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    controller = new ImageController(imageService, recipeService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  void getImageForm() throws Exception {
    // given
    RecipeCommand recipeCommand = RecipeCommand.builder().id(1L).build();
    when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
    // when
    mockMvc.perform(get("/recipe/1/image"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("recipe"))
            .andExpect(view().name("recipe/imageUploadForm"));
    // then
    verify(recipeService, times(1)).findCommandById(anyLong());
  }

  @Test
  void handleImagePost() throws Exception {
    // given
    MockMultipartFile mockMultipartFile = new MockMultipartFile("imageFile",
            "testing.txt",
            "text/plain",
            "Mouhieddine.dev".getBytes());
    // when
    mockMvc.perform(multipart("/recipe/1/image").file(mockMultipartFile))
            .andExpect(status().is3xxRedirection())
            .andExpect(header().string("Location", "/recipe/1/image"));
    // then
    verify(imageService, times(1)).saveImageFile(anyLong(), any());
  }
}