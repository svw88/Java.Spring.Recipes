package app.domain.recipes;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository
{
    public Long saveRecipe(RecipeEntity entity);

    public Optional<RecipeEntity> getRecipeById(Long aLong);

    public void deleteRecipeById(Long aLong);

    public List<RecipeEntity> queryRecipeByName(String name);

    public List<RecipeEntity> queryRecipeByCategory(String name);
}


