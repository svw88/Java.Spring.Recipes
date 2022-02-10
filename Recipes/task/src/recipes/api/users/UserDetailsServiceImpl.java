package recipes.api.users;

import recipes.domain.users.UsersService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UsersService usersService;

    public UserDetailsServiceImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = usersService.findUserByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Not found: " + username);
        }

        return user.get();
    }

}
