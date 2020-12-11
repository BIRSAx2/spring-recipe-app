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
class NotesToNotesCommandTest {
  private final Long NOTES_ID = 1L;
  private final String RECIPE_NOTES = "description";

  NotesToNotesCommand converter;

  @BeforeEach
  void setUp() {
    converter = new NotesToNotesCommand();
  }

  @Test
  void testNullObject() {
    assertNull(converter.convert(null));
  }

  @Test
  void testEmptyObject() {
    assertNotNull(converter.convert(new Notes()));
  }

  @Test
  void convert() {
    // given
    Notes notes = new Notes();
    notes.setId(NOTES_ID);
    notes.setRecipeNotes(RECIPE_NOTES);
    // when
    NotesCommand notesCommand = converter.convert(notes);
    // then
    assertNotNull(notesCommand);
    assertEquals(NOTES_ID, notesCommand.getId());
    assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
  }

}