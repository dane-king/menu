package com.daneking.recipemain.recipe;

import com.daneking.recipemain.recipe.category.RecipeCategory;
import com.daneking.recipemain.recipe.ingredient.Ingredient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String recipeName;

    @Enumerated(EnumType.STRING)
    private RecipeCategory recipeCategory;

    public Recipe(String recipeName, RecipeCategory category, List<Ingredient> ingredientList) {
        this.recipeName = recipeName;
        this.recipeCategory = category;
        this.ingredients = ingredientList;
    }
    public Recipe(){
        super();
        //no op constructor, set defaults here
    }

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="recipe_ingredient",
    joinColumns = @JoinColumn(name="recipe_id"),
    inverseJoinColumns = @JoinColumn(name="ingredient_id"))
    private List<Ingredient> ingredients=new ArrayList<>();




    public String getRecipeName() {
        return recipeName;
    }

    public Long getId() {
        return this.id;
    }

    public RecipeCategory getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(RecipeCategory recipeCategory) {
        this.recipeCategory=recipeCategory;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return id.equals(recipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                ", recipeCategory=" + recipeCategory +
                '}';
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
    }
    public List<Ingredient> getIngredients(){
        return Collections.unmodifiableList(this.ingredients);
    }
}
