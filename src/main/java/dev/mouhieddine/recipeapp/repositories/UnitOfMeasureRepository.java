package dev.mouhieddine.recipeapp.repositories;

import dev.mouhieddine.recipeapp.domain.Recipe;
import dev.mouhieddine.recipeapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
  Optional<UnitOfMeasure> findByDescription(String description);
}
