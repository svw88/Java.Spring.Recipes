package recipes.repository.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStore extends CrudRepository<User, Long> {

    @Query("select u from User u where UPPER(u.username) = UPPER(?1)")
    List<User> findUsersByUsername(String name);
}
