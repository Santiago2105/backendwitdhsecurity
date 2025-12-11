package pe.edu.upc.ea2025.sacjServiceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.ea2025.sacjEntities.User;
import pe.edu.upc.ea2025.sacjSecurity.UserSecurity;
import pe.edu.upc.ea2025.sacjServices.UserService;

@Service
public class UserDetailsServiceImpl implements  UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userFound = userService.findByUsername(username);
        return new UserSecurity(userFound);
    }
}
