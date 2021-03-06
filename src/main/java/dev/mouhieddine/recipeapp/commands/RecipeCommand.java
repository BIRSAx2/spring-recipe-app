package dev.mouhieddine.recipeapp.commands;

import dev.mouhieddine.recipeapp.domain.Difficulty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Mouhieddine.dev
 * @since : 12/11/2020, Friday
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeCommand {
  private Long id;
  private String description;
  private Integer prepTime;
  private Integer cookTime;
  private Integer servings;
  private String source;
  private String url;
  private String directions;
  private Set<IngredientCommand> ingredients = new HashSet<>();
  private Difficulty difficulty;
  private NotesCommand notes;
  private Set<CategoryCommand> categories = new HashSet<>();
  private Byte[] image;
}
