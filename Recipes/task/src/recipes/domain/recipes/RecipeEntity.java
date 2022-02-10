package recipes.domain.recipes;

import recipes.domain.common.DomainError;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RecipeEntity {

    private Long id;
    private String name;
    private String description;
    private String category;
    private String author;
    private LocalDateTime date;
    private String[] ingredients;
    private String[] directions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getDirections() {
        return directions;
    }

    public void setDirections(String[] directions) {
        this.directions = directions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public RecipeEntity(Long id, String author, String name, String description, String category, LocalDateTime date, String[] ingredients, String[] directions) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.description = description;
        this.category = category;
        this.date = date;
        this.ingredients = ingredients;
        this.directions = directions;

        validate();
    }

    private void validate() {
        var errors = new ArrayList<DomainError>();

        if (name == null || name.isBlank() || name.isEmpty()) {
            errors.add(new DomainError("name is null or empty"));
        }

        if (description == null || description.isBlank() || description.isEmpty()) {
            errors.add(new DomainError("description is null or empty"));
        }

        if (category == null || category.isBlank() || category.isEmpty()) {
            errors.add(new DomainError("category is null or empty"));
        }

        if (ingredients == null || ingredients.length < 1) {
            errors.add(new DomainError("ingredients is null"));
        }

        if (directions == null || directions.length < 1) {
            errors.add(new DomainError("directions is null"));
        }

        if  (errors.size() > 0) {
            throw new InvalidRecipeDetailsException(errors.toString());
        }


    }

}
