package dev.mouhieddine.recipeapp.converters;

import dev.mouhieddine.recipeapp.commands.NotesCommand;
import dev.mouhieddine.recipeapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/11/2020, Friday
 **/
class NotesCommandToNotesTest {
  private final Long NOTES_ID = 1L;
  private final String RECIPE_NOTES = "description";

  NotesCommandToNotes converter;

  @BeforeEach
  void setUp() {
    converter = new NotesCommandToNotes();
  }

  @Test
  void testNullObject() {
    assertNull(converter.convert(null));
  }

  @Test
  void testEmptyObject() {
    assertNotNull(converter.convert(new NotesCommand()));
  }

  @Test
  void convert() {
    // given
    NotesCommand notesCommand = new NotesCommand();
    notesCommand.setId(NOTES_ID);
    notesCommand.setRecipeNotes(RECIPE_NOTES);
    // when
    Notes notes = converter.convert(notesCommand);
    // then
    assertNotNull(notes);
    assertEquals(NOTES_ID,notes.getId());
    assertEquals(RECIPE_NOTES,notes.getRecipeNotes());
  }
}