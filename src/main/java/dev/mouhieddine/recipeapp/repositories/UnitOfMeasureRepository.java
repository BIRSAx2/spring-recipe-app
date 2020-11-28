package dev.mouhieddine.recipeapp.repositories;

import dev.mouhieddine.recipeapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
}
