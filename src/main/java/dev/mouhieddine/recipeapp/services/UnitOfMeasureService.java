package dev.mouhieddine.recipeapp.services;

import dev.mouhieddine.recipeapp.commands.UnitOfMeasureCommand;
import dev.mouhieddine.recipeapp.domain.UnitOfMeasure;

import java.util.Set;

/**
 * @author : Mouhieddine.dev
 * @since : 12/13/2020, Sunday
 **/
public interface UnitOfMeasureService {
  Set<UnitOfMeasureCommand> getUnitOfMeasures();
}
