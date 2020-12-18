package dev.mouhieddine.recipeapp.repositories;

import dev.mouhieddine.recipeapp.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : Mouhieddine.dev
 * @since : 12/17/2020, Thursday
 **/
public interface IngredientRepository extends CrudRepository<Ingredient,Long> {
}
