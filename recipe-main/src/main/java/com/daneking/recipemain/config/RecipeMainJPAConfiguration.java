package com.daneking.recipemain.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.daneking.recipemain.recipe"})
public class RecipeMainJPAConfiguration {
}
