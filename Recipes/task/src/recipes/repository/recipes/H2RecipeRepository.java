package recipes.repository.recipes;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import recipes.domain.recipes.RecipeEntity;
import recipes.domain.recipes.RecipeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class H2RecipeRepository implements RecipeRepository {

    private RecipeStore recipeStore;

    public H2RecipeRepository(RecipeStore recipeStore) {
        this.recipeStore = recipeStore;
    }

    public Long saveRecipe(RecipeEntity entity) {
        var result = recipeStore.save(new Recipe(entity));

        return result.getId();
    }

    public Optional<RecipeEntity> getRecipeById(Long id) {
        var recipeOptional = recipeStore.findById(id);

        if (recipeOptional.isEmpty()) {
            return Optional.empty();
        }

        var result = recipeOptional.get();

        return Optional.of(
                new RecipeEntity(
                        result.getId(),
                        result.getAuthor(),
                        result.getName(),
                        result.getDescription(),
                        result.getCategory(),
                        result.getDate(),
                        result.getIngredients(),
                        result.getDirections()
                )
        );
    }



    public void deleteRecipeById(Long id) {

        recipeStore.deleteById(id);
    }

    public List<RecipeEntity> queryRecipeByName(String name) {
        return recipeStore.findByName(name, Sort.by(Sort.Order.desc("date")))
                .stream()
                .map(result -> new RecipeEntity(
                        result.getId(),
                        result.getAuthor(),
                        result.getName(),
                        result.getDescription(),
                        result.getCategory(),
                        result.getDate(),
                        result.getIngredients(),
                        result.getDirections()
                )).collect(Collectors.toList());
    }

    public List<RecipeEntity> queryRecipeByCategory(String category) {
        return recipeStore.findByCategory(category, Sort.by(Sort.Order.desc("date")))
                .stream()
                .map(result -> new RecipeEntity(
                        result.getId(),
                        result.getAuthor(),
                        result.getName(),
                        result.getDescription(),
                        result.getCategory(),
                        result.getDate(),
                        result.getIngredients(),
                        result.getDirections()
                )).collect(Collectors.toList());
    }

}
