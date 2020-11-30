package dev.mouhieddine.recipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude="recipe")
@Entity
public class Notes {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Lob  // will be stored in glob field
  private String recipeNotes;

  @OneToOne
  private Recipe recipe;


}
