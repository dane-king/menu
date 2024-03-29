package com.daneking.recipemain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(com.daneking.recipemain.config.RecipeMainJPAConfiguration.class)
public class RecipeMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeMainApplication.class, args);
    }

}
