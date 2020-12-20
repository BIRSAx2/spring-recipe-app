package dev.mouhieddine.recipeapp.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Mouhieddine.dev
 * @since : 12/20/2020, Sunday
 **/
public interface ImageService {
  void saveImageFile(Long recipeId, MultipartFile image);
}
