package recipes.domain.users;

public class InvalidUserDetailsException extends RuntimeException {
    public InvalidUserDetailsException(String message) {
        super(message);
    }
}
