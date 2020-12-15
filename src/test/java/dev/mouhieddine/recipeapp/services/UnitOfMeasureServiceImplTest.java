package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.commands.UnitOfMeasureCommand;
import dev.mouhieddine.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import dev.mouhieddine.recipeapp.domain.UnitOfMeasure;
import dev.mouhieddine.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/13/2020, Sunday
 **/
@ExtendWith(MockitoExtension.class)
class UnitOfMeasureServiceImplTest {
  UnitOfMeasureToUnitOfMeasureCommand converter = new UnitOfMeasureToUnitOfMeasureCommand();
  UnitOfMeasureService service;
  @Mock
  UnitOfMeasureRepository repository;

  @BeforeEach
  void setUp() {
    service = new UnitOfMeasureServiceImpl(repository, converter);
  }

  @Test
  void getUnitOfMeasures() {
    // given
    Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
    unitOfMeasures.add(UnitOfMeasure.builder().id(1L).build());
    unitOfMeasures.add(UnitOfMeasure.builder().id(2L).build());
    when(repository.findAll()).thenReturn(unitOfMeasures);
    // when
    Set<UnitOfMeasureCommand> commands = service.getUnitOfMeasures();
    // then
    assertEquals(unitOfMeasures.size(), commands.size());
    verify(repository, times(1)).findAll();
  }
}