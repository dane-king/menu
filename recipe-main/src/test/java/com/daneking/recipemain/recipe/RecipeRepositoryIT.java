package com.daneking.recipemain.recipe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
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
@Sql("classpath:data.sql")
public class RecipeRepositoryIT {
    //for some reason using the test entity manager and flush does not do the ame thing
//    @Autowired
//    private TestEntityManager entityManager;

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void saveRecipeReturnRecipeWithId() {

        Recipe _recipe = recipeRepository.save(createRecipe(null));
        assertThat(_recipe.getId(), notNullValue());
    }

    @Test
    public void findByNameReturnsRecipe() {
        String recipeName = "Recipe Name One";
        Recipe recipe = createRecipe(recipeName);
        recipeRepository.saveAndFlush(recipe);

        Optional<Recipe> _recipe = recipeRepository.findOneByName(recipeName);

        assertThat(_recipe.get(), equalTo(recipe));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void cannotCreateRecipesOfSameName() {
        String recipeName = "Recipe Name";

        recipeRepository.saveAndFlush(createRecipe(recipeName));
        recipeRepository.saveAndFlush(createRecipe(recipeName));
        fail();
    }

    private Recipe createRecipe(String recipeName) {
        if (StringUtils.isEmpty(recipeName)) {
            return new Recipe();
        }
        return new Recipe(recipeName);
    }
}
