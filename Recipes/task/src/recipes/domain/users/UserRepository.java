package recipes.domain.users;

import java.util.List;

public interface UserRepository {

    void saveUser(UserEntity entity);

    List<UserEntity> findUsersByUsername(String userName);
}
