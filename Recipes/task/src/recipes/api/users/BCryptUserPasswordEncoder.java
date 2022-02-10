package recipes.api.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.domain.users.UserPasswordEncoder;

@Service
public class BCryptUserPasswordEncoder extends BCryptPasswordEncoder implements UserPasswordEncoder {


    @Override
    public String encode(String password) {
        return super.encode(password);
    }
}
