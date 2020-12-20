package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.domain.Recipe;
import dev.mouhieddine.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * @author : Mouhieddine.dev
 * @since : 12/20/2020, Sunday
 **/
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

  private final RecipeRepository recipeRepository;

  public ImageServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  public static byte[] toPrimitives(Byte[] oBytes) {

    byte[] bytes = new byte[oBytes.length];
    for (int i = 0; i < oBytes.length; i++) {
      bytes[i] = oBytes[i];
    }
    return bytes;

  }

  public static Byte[] toObjects(byte[] bytesPrim) {

    Byte[] bytes = new Byte[bytesPrim.length];
    int i = 0;
    for (byte b : bytesPrim) bytes[i++] = b; //Autoboxing
    return bytes;

  }

  @Override
  public void saveImageFile(Long recipeId, MultipartFile image) {
    log.debug("Received an image file");
    Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
    if (optionalRecipe.isEmpty()) throw new RuntimeException("Recipe not found for id:" + recipeId);
    Recipe recipe = optionalRecipe.get();
    try {
      recipe.setImage(toObjects(image.getBytes()));
    } catch (IOException e) {
      //todo handle better
      log.error("Error during the saving of the image file");
    }
    recipeRepository.save(recipe);
  }
}
