package app.domain.recipes;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipesService {

    private final RecipeRepository repository;

    public RecipesService(RecipeRepository repository) {
        this.repository = repository;
    }

    public long addRecipe(
            String author,
            String name,
            String description,
            String category,
            String[] ingredients,
            String[] directions) {

        var recipeEntity = new RecipeEntity(
                null,
                author,
                name,
                description,
                category,
                null,
                ingredients,
                directions
        );

        return repository.saveRecipe(recipeEntity);
    }

    public void updateRecipe(Long id, String author, String name, String description, String category, String[] ingredients, String[] directions) {

        var recipe = repository.getRecipeById(id);

        if (recipe.isEmpty()) {
           throw new RecipeNotFoundException();
        }

        if (!recipe.get().getAuthor().equals(author)) {
            throw new InvalidRecipeAuthorException();
        }
        var recipeEntity = new RecipeEntity(
                id,
                author,
                name,
                description,
                category,
                null,
                ingredients,
                directions
        );
        repository.saveRecipe(recipeEntity);

    }

    public RecipeEntity getRecipe(Long id) {
        var recipe = repository.getRecipeById(id);

        if (recipe.isEmpty()) {
            throw new RecipeNotFoundException();
        }

        return recipe.get();
    }

    public void deleteRecipe(Long id, String author) {
        var recipe = repository.getRecipeById(id);

        if (recipe.isEmpty()) {
            throw new RecipeNotFoundException();
        }

        if (!recipe.get().getAuthor().equals(author)) {
            throw new InvalidRecipeAuthorException();
        }

        repository.deleteRecipeById(id);
    }

    public List<RecipeEntity> queryRecipeByName(String name) {
        return repository.queryRecipeByName(name);
    }

    public List<RecipeEntity> queryRecipeByCategory(String category) {
        return repository.queryRecipeByCategory(category);
    }
}
