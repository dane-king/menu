package com.daneking.recipemain.recipe.category;

import com.daneking.recipemain.recipe.Recipe;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="recipe_categories")
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public RecipeCategory(String categoryName) {
        this.categoryName = categoryName;
    }
    //noop constructor


    @Column(unique = true)
    private String categoryName;

    @OneToMany(
            mappedBy = "recipeCategory",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Recipe> recipeList=new ArrayList<>();

    public List<Recipe> getRecipeList(){
        return Collections.unmodifiableList(this.recipeList);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return categoryName;
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
