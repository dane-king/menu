package com.daneking.recipemain.recipe;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public RecipeCategory(String name) {
        this.name = name;
    }
    //noop constructor
    public RecipeCategory() {
    }
    @Column(unique = true)
    private String name;

    @OneToMany(
            mappedBy = "recipeCategory",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Recipe> recipeList=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addRecipe(Recipe recipe){
        recipeList.add(recipe);
        recipe.setRecipeCategory(this);
    }
    public void removeRecipe(Recipe recipe){
        recipeList.remove(recipe);
        recipe.setRecipeCategory(null);
    }
}
