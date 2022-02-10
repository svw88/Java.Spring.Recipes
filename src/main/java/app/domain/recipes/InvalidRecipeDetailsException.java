package app.domain.recipes;

public class InvalidRecipeDetailsException extends RuntimeException {
    public InvalidRecipeDetailsException(String message) {
        super(message);
    }
}
