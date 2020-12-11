package dev.mouhieddine.recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Mouhieddine.dev
 * @since : 12/11/2020, Friday
 **/
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
  private Long id;
  private String recipeNotes;
}
