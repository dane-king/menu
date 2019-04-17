package com.daneking.recipemain.recipe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("classpath:recipe/data.sql")
public class RecipeRepositoryIT {
    @Autowired
    private RecipeRepository recipeRepository;
    //defined in data.sql
    public static final String RECIPE_NAME = "Recipe Number One";

    @Test
    public void saveRecipeReturnRecipeWithId() {

        Recipe _recipe = recipeRepository.save(createRecipe(null));
        assertThat(_recipe.getId(), notNullValue());
    }

    @Test
    public void findByNameReturnsRecipe() {
        Optional<Recipe> _recipe = recipeRepository.findOneByRecipeName(RECIPE_NAME);

        assertThat(_recipe.get().getRecipeName(), equalTo(RECIPE_NAME));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void cannotCreateRecipesOfSameName() {
        recipeRepository.saveAndFlush(createRecipe(RECIPE_NAME));
        fail();
    }

    //Test creating with category
    //Create tests for category, ie findall recipes by category

    private Recipe createRecipe(String recipeName) {
        if (StringUtils.isEmpty(recipeName)) {
            return new Recipe();
        }
        return new Recipe(recipeName);
    }
}
