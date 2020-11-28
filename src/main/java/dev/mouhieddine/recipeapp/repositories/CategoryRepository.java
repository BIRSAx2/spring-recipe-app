package dev.mouhieddine.recipeapp.repositories;

import dev.mouhieddine.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
