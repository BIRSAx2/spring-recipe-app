package dev.mouhieddine.recipeapp.commands;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author : Mouhieddine.dev
 * @since : 12/11/2020, Friday
 **/
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class IngredientCommand {
  private Long id;
  private Long recipeId;
  private String description;
  private BigDecimal amount;
  private UnitOfMeasureCommand unitOfMeasure;
}
