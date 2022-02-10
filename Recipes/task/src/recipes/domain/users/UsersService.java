package recipes.domain.users;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private final UserRepository repository;
    private final UserPasswordEncoder passwordEncoder;

    public UsersService(UserRepository repository, UserPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(UserEntity user) {
        var users = repository.findUsersByUsername(user.getUsername());

        if (users.isEmpty())
        {
            user.encodePassword(passwordEncoder);
            this.repository.saveUser(user);
        } else {
            throw new UserAlreadyExistsException();
        }

    }

    public Optional<UserEntity> findUserByUsername(String userName) {
        var users = repository.findUsersByUsername(userName);
        if (users.isEmpty())
        {
            return Optional.empty();
        }

        if (users.size() > 1)
        {
            throw new RuntimeException("Multiple users found");
        }

        return Optional.of(users.get(0));
    }
}
