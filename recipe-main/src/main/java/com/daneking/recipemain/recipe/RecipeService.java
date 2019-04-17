package com.daneking.recipemain.recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);

    }
}
