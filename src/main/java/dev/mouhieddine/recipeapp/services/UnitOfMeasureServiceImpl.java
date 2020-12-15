package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.commands.UnitOfMeasureCommand;
import dev.mouhieddine.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import dev.mouhieddine.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Mouhieddine.dev
 * @since : 12/13/2020, Sunday
 **/
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

  private final UnitOfMeasureRepository repository;
  private final UnitOfMeasureToUnitOfMeasureCommand converter;

  public UnitOfMeasureServiceImpl(UnitOfMeasureRepository repository, UnitOfMeasureToUnitOfMeasureCommand converter) {
    this.repository = repository;
    this.converter = converter;
  }

  @Override
  public Set<UnitOfMeasureCommand> getUnitOfMeasures() {
    Set<UnitOfMeasureCommand> unitOfMeasures = new HashSet<>();
    repository.findAll().iterator().forEachRemaining(el -> unitOfMeasures.add(converter.convert(el)));
    return unitOfMeasures;
  }
}
