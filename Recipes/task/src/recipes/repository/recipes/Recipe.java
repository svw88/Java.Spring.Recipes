package recipes.repository.recipes;

import recipes.domain.recipes.RecipeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "author")
    private String author;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "ingredients")
    private String[] ingredients;

    @Column(name = "directions")
    private String[] directions;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Recipe() {
    }

    public Recipe(RecipeEntity recipeEntity) {
        if (recipeEntity.getId() != null) {
            id = recipeEntity.getId();
        }
        author = recipeEntity.getAuthor();
        name = recipeEntity.getName();
        description = recipeEntity.getDescription();
        category = recipeEntity.getCategory();
        date = LocalDateTime.now();
        ingredients = recipeEntity.getIngredients();
        directions = recipeEntity.getDirections();
    }



}
