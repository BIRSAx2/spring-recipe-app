package dev.mouhieddine.recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude= {"ingredients", "notes"})
@ToString(exclude= {"ingredients", "notes"})
@Entity
public class Recipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // special type in sql db. Like an AUTO_INCREMENT field on mySql
  private Long id;
  private String description;
  private Integer prepTime;
  private Integer cookTime;
  private Integer servings;
  private String source;
  private String url;
  @Lob
  private String directions;
  @Lob // will be stored in a blob field
  private Byte[] image;
  @Enumerated(value = EnumType.STRING)
  // ordinary will use the "index" to persist 1->EASY, string will use the string name so "EASY"
  private Difficulty difficulty;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe") // defines the property where the recipe will be stored in the child
  private Set<Ingredient> ingredients=new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL) // on delete cascade on update cascade
  private Notes notes;

  @ManyToMany
  @JoinTable(
          name = "recipe_category", // name of the table
          joinColumns = @JoinColumn(name = "recipe_id"), // name of the flied
          inverseJoinColumns = @JoinColumn(name = "category_id") // name of the flied
  )
  private Set<Category> categories = new HashSet<>();

  public void setNotes(Notes notes) {
    notes.setRecipe(this);
    this.notes = notes;
  }

  public void addIngredient(Ingredient ingredient) {
    ingredient.setRecipe(this);
    ingredients.add(ingredient);
  }
  public void addCategory(Category category){
    categories.add(category);
  }
}
