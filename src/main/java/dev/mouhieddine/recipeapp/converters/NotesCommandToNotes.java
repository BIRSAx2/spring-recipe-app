package dev.mouhieddine.recipeapp.converters;

import dev.mouhieddine.recipeapp.commands.NotesCommand;
import dev.mouhieddine.recipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * @author : Mouhieddine.dev
 * @since : 12/11/2020, Friday
 **/
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
  @Synchronized
  @Nullable
  @Override
  public Notes convert(NotesCommand notesCommand) {
    if (notesCommand == null) return null;

    Notes notes = new Notes();
    notes.setId(notesCommand.getId());
    notes.setRecipeNotes(notesCommand.getRecipeNotes());

    return notes;
  }
}
