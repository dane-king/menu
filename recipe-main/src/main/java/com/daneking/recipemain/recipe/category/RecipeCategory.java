package com.daneking.recipemain.recipe.category;

public enum RecipeCategory {
    MAIN("Main"),APPETIZER("Appetizer");

    private final String name;

    RecipeCategory(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
