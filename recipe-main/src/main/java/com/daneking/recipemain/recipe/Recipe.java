package com.daneking.recipemain.recipe;

import com.daneking.recipemain.recipe.category.RecipeCategory;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String recipeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipeCategory_id")
    private RecipeCategory recipeCategory;

    public Recipe(String recipeName) {
        this.recipeName = recipeName;
    }

    //noop constructor
    public Recipe() {
    }

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


}
