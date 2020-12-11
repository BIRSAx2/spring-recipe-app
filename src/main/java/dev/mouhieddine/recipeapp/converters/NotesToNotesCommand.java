package dev.mouhieddine.recipeapp.converters;

import dev.mouhieddine.recipeapp.commands.NotesCommand;
import dev.mouhieddine.recipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author : Mouhieddine.dev
 * @since : 12/11/2020, Friday
 **/
@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
  @Synchronized
  @Nullable
  @Override
  public NotesCommand convert(Notes notes) {
    if (notes == null) return null;

    NotesCommand notesCommand = new NotesCommand();
    notesCommand.setId(notes.getId());
    notesCommand.setRecipeNotes(notes.getRecipeNotes());

    return notesCommand;
  }
}
