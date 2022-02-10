package recipes.api.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recipes.domain.users.InvalidUserDetailsException;
import recipes.domain.users.UserAlreadyExistsException;
import recipes.domain.users.UserEntity;
import recipes.domain.users.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UsersController {
    Logger logger = LoggerFactory.getLogger(UsersController.class);
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @PostMapping("/api/register")
    public void  addUser(@RequestBody UserDto recipe) {
        try {
            this.usersService.addUser(new UserEntity(recipe.getEmail(),recipe.getPassword()));
        } catch (InvalidUserDetailsException | UserAlreadyExistsException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

}
