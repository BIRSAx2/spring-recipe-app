package dev.mouhieddine.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Mouhieddine.dev
 * @since : 12/20/2020, Sunday
 **/
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
  @Override
  public void saveImageFile(Long recipeId, MultipartFile image) {
    log.debug("Received an image file");
  }
}
