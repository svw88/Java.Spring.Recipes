package recipes.domain.users;

public interface UserPasswordEncoder {
    String encode(String password);

}
