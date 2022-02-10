package app.repository.users;

import app.domain.users.UserEntity;
import app.domain.users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class H2UserRepository implements UserRepository {


    private final UserStore userStore;

    public H2UserRepository(UserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public void saveUser(UserEntity entity) {
        userStore.save(new User(entity));
    }

    @Override
    public List<UserEntity> findUsersByUsername(String userName) {
        return userStore.findUsersByUsername(userName)
                .stream()
                .map(x ->
                new UserEntity(
                       x.getUsername(),
                       x.getPassword())
                ).collect(Collectors.toList());

    }
}
