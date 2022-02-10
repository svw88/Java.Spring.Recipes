package recipes.api.recipes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import recipes.api.users.UsersController;
import recipes.domain.recipes.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class RecipesController {

    Logger logger = LoggerFactory.getLogger(UsersController.class);
    private final RecipesService recipesService;

    public RecipesController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }


    @GetMapping("/api/recipe/{id}")
    public RecipeDto getRecipe(@PathVariable Long id) {
        try {
            var recipeEntity = recipesService.getRecipe(id);

            return new RecipeDto(
                    recipeEntity.getName(),
                    recipeEntity.getDescription(),
                    recipeEntity.getIngredients(),
                    recipeEntity.getDirections(),
                    recipeEntity.getCategory(),
                    recipeEntity.getDate());
        } catch (RecipeNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (InvalidRecipeDetailsException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/api/recipe/new")
    public Map<String, Long>  addRecipe(@AuthenticationPrincipal UserDetails details, @RequestBody RecipeDto recipe) {

        try {
            var id = recipesService.addRecipe(
                details.getUsername(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getCategory(),
                recipe.getIngredients(),
                recipe.getDirections()
            );

            return Map.of("id", id);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }



    }

    @DeleteMapping("/api/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@AuthenticationPrincipal UserDetails details, @PathVariable Long id) {

       try {
           recipesService.deleteRecipe(id, details.getUsername());
       } catch (RecipeNotFoundException ex) {
           logger.error(ex.getMessage());
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       } catch (InvalidRecipeAuthorException ex) {
           logger.error(ex.getMessage());
           throw new ResponseStatusException(HttpStatus.FORBIDDEN);
       } catch (Exception ex) {
           logger.error(ex.getMessage());
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
       }

    }

    @PutMapping("/api/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putRecipe(@AuthenticationPrincipal UserDetails details, @PathVariable Long id, @RequestBody RecipeDto recipe) {

        try {
            recipesService.updateRecipe(
                    id,
                    details.getUsername(),
                    recipe.getName(),
                    recipe.getDescription(),
                    recipe.getCategory(),
                    recipe.getIngredients(),
                    recipe.getDirections()
            );

        } catch (RecipeNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (InvalidRecipeAuthorException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/recipe/search")
    public List<RecipeDto> getRecipe(@RequestParam(required = false) String name, @RequestParam(required = false) String category) {

        if(category != null && name != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (category != null) {
            return recipesService.queryRecipeByCategory(category)
                    .stream()
                    .map(recipeEntity -> new RecipeDto
                            (
                                recipeEntity.getName(),
                                recipeEntity.getDescription(),
                                recipeEntity.getIngredients(),
                                recipeEntity.getDirections(),
                                recipeEntity.getCategory(),
                                recipeEntity.getDate()
                            )
                    ).collect(Collectors.toList());
        }

        if (name != null) {
            return recipesService.queryRecipeByName(name)
                    .stream()
                    .map(recipeEntity -> new RecipeDto
                            (
                                    recipeEntity.getName(),
                                    recipeEntity.getDescription(),
                                    recipeEntity.getIngredients(),
                                    recipeEntity.getDirections(),
                                    recipeEntity.getCategory(),
                                    recipeEntity.getDate()
                            )
                    ).collect(Collectors.toList());
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

}
