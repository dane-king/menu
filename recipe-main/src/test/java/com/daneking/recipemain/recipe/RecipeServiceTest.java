package com.daneking.recipemain.recipe;

import com.daneking.recipemain.recipe.category.RecipeCategory;
import com.daneking.recipemain.recipe.ingredient.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Test
    public void addRecipeCallsSaveOnRepository() {
        List<Ingredient> ingredientList= Arrays.asList(
                new Ingredient("Carrots"),
                new Ingredient("Honey"));

        Recipe recipe = new RecipeFactory(null)
                .with(bld->{
                        bld.category=RecipeCategory.MAIN;
                        bld.ingredientList=ingredientList;
                })
                .create();
        recipeService.addRecipe(recipe);
        verify(recipeRepository).save(eq(recipe));

    }
}
