package recipes.repository.users;

import org.springframework.stereotype.Service;
import recipes.domain.users.UserEntity;
import recipes.domain.users.UserRepository;

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
