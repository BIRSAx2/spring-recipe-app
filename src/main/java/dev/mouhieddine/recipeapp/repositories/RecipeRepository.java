package dev.mouhieddine.recipeapp.repositories;

import dev.mouhieddine.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {

}
