package com.daneking.recipemain.recipe;

import com.daneking.recipemain.recipe.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void addRecipeCallsSaveOnRepository() {
        Recipe recipe = new Recipe();
        recipeService.addRecipe(recipe);
        verify(recipeRepository).save(eq(recipe));

    }
}
