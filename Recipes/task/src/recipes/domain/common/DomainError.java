package recipes.domain.common;

public class DomainError {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DomainError(String message) {
        this.message = message;
    }

    private String message;
}
