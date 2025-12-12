package pe.edu.upc.ea2025.sacjControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ea2025.sacjDtos.DTOToken;
import pe.edu.upc.ea2025.sacjDtos.DTOUser;
import pe.edu.upc.ea2025.sacjEntities.User;
import pe.edu.upc.ea2025.sacjSecurity.JwtUtilService;
import pe.edu.upc.ea2025.sacjSecurity.UserSecurity;
import pe.edu.upc.ea2025.sacjServices.UserService;

import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/cjsa")

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilService jwtUtilService;


    @PostMapping("/users")
    public ResponseEntity<DTOUser> register(@RequestBody DTOUser user){
        user=userService.add(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<DTOToken> login(@RequestBody DTOUser user) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        UserSecurity userSecurity = (UserSecurity)userDetailsService.loadUserByUsername(user.getUsername());

        String jwt = jwtUtilService.generateToken(userSecurity);
        Long id = userSecurity.getUser().getId();
        String authorities = userSecurity.getUser().getAuthorities()
                .stream()
                .map( a-> a.getName())
                .collect(Collectors.joining(";","",""));

        return new ResponseEntity<>(new DTOToken(jwt,id, authorities), HttpStatus.OK);
    }
}
