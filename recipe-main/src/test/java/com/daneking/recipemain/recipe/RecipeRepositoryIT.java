package com.daneking.recipemain.recipe;

import com.daneking.recipemain.common.LambdaMatcher;
import com.daneking.recipemain.recipe.category.RecipeCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("classpath:recipe/data.sql")
public class RecipeRepositoryIT {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private EntityManager entityManager;
    //defined in data.sql
    public static final String RECIPE_NAME = "Recipe Number One";

    @Test
    public void saveRecipeWithAllDataPopulated() {

        Recipe sourceRecipe = new RecipeFactory("MyName")
                .with(bld->{
                    bld.category= RecipeCategory.MAIN;
                })
                .create();
        recipeRepository.saveAndFlush(sourceRecipe);
//        if you want to update the object outside must detach first
//        entityManager.detach(sourceRecipe);
//        sourceRecipe.setRecipeCategory(RecipeCategory.APPETIZER);
        Optional<Recipe> recipe = recipeRepository.findById(sourceRecipe.getId());

        assertThat(recipe.get(), recipe_equals.apply(sourceRecipe));
    }

    private Function<Recipe, LambdaMatcher<Recipe>> recipe_equals = (recipe) -> new LambdaMatcher<>((r) ->
            r.getRecipeName().equals(recipe.getRecipeName())
                    && r.getRecipeCategory().equals(recipe.getRecipeCategory()), String.format("Recipes must have same name and category\nExpected: %s\n", recipe.toString()));

    @Test
    public void findByNameReturnsRecipe() {
        Optional<Recipe> _recipe = recipeRepository.findOneByRecipeName(RECIPE_NAME);

        assertThat(_recipe.get().getRecipeName(), equalTo(RECIPE_NAME));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void cannotCreateRecipesOfSameName() {
        recipeRepository.saveAndFlush(new RecipeFactory(RECIPE_NAME).create());
        fail();
    }

}
