package com.daneking.recipemain.recipe.ingredient;

import javax.persistence.*;

@Entity
@Table(name="ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Long getId() {
        return id;
    }

    private String ingredientName;


}
