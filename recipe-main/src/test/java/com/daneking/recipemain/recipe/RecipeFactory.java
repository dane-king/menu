package com.daneking.recipemain.recipe;

import com.daneking.recipemain.recipe.category.RecipeCategory;
import com.daneking.recipemain.recipe.ingredient.Ingredient;

import java.util.List;
import java.util.function.Consumer;

//Test creating with category
//Create tests for category, ie findall recipes by category
public class RecipeFactory {

    String recipeName;
    RecipeCategory category;
    List<Ingredient> ingredientList;

    public RecipeFactory(String recipeName){
        this.recipeName=recipeName;
    }

    public RecipeFactory with(
            Consumer<RecipeFactory> builderFunction){
        builderFunction.accept(this);
        return this;
    };
    public Recipe create(){
        return new Recipe(recipeName, category, ingredientList);
    }
//    public RecipeFactory(String recipeName, List<Ingredient> ingredientList) {
//        recipeName = this(recipeName);
//        ingredientList.forEach(i->this.recipe.addIngredient(i));
//    }
//    public RecipeFactory(String recipeName, RecipeCategory recipeCategory) {
//        recipe = this(recipeName);
//        recipe.setRecipeCategory(recipeCategory);
//    }
//
//    public RecipeFactory(String recipeName) {
//        if (StringUtils.isEmpty(recipeName)) {
//            recipe=new Recipe();
//        }
//    }
}
