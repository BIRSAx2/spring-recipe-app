package dev.mouhieddine.recipeapp.commands;

import dev.mouhieddine.recipeapp.domain.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author : Mouhieddine.dev
 * @since : 12/11/2020, Friday
 **/
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
  private Long id;
  private String description;
  private BigDecimal amount;
  private UnitOfMeasureCommand unitOfMeasure;
}
