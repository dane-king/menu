package com.daneking.recipemain.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeCategoryRepository extends JpaRepository<Recipe,Long> {
}
