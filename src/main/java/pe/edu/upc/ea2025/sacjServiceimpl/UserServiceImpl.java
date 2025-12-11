package pe.edu.upc.ea2025.sacjServiceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.ea2025.sacjDtos.DTOUser;
import pe.edu.upc.ea2025.sacjEntities.Authority;
import pe.edu.upc.ea2025.sacjEntities.User;
import pe.edu.upc.ea2025.sacjExceptions.IncompleteDataException;
import pe.edu.upc.ea2025.sacjExceptions.KeyRepeatedDataExeception;
import pe.edu.upc.ea2025.sacjExceptions.ResourceNotFoundException;
import pe.edu.upc.ea2025.sacjRepositories.UserRepository;
import pe.edu.upc.ea2025.sacjServices.AuthorityService;
import pe.edu.upc.ea2025.sacjServices.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityService authorityService;


    /* Viene de UserService */

    @Override
    public User findById(Long id) {
        User userFound = userRepository.findById(id).orElse(null);
        if(userFound == null){
            throw new ResourceNotFoundException("User with Id: "+ id+" not found");
        }
        return userFound;
    }

    @Override
    public DTOUser findByIdDTO(Long id) {
        User user = findById(id);
        DTOUser userFound = new DTOUser(user.getId(), user.getUsername(), user.getPassword(),
                        user.getAuthorities()
                        .stream()
                        .map( a-> a.getName())
                        .collect(Collectors.joining(";","",""))

                );
        return userFound;
    }

    @Override
    public User findByUsername(String username) {
        User userFound = userRepository.findByUsername(username);
        if(userFound == null){
            throw new ResourceNotFoundException("User with Username: "+ username+" not found");
        }
        return userFound;
    }

    private List<Authority>  authoritiesFromString(String authorities){
        List<Authority> authoritiesList = new ArrayList<>();
        List<String> authoritiesStringList = Arrays.stream(authorities.split(";")).toList();
        for(String authorityString : authoritiesStringList){
            Authority authority = authorityService.findByName(authorityString);
            if (authority != null){
                authoritiesList.add(authority);
            }
        }
        return authoritiesList;
    }

    @Override
    public User addUser(DTOUser dtoUser) {


        if (dtoUser.getUsername()==null||dtoUser.getUsername().isBlank()) {
            throw new IncompleteDataException("Username can not be empty");
        }
        if (dtoUser.getPassword()==null||dtoUser.getPassword().isBlank()) {
            throw new IncompleteDataException("Password can not be empty");
        }
        if (dtoUser.getAuthorities()==null||dtoUser.getAuthorities().isBlank()) {
            throw new IncompleteDataException("Authorities can not be empty");
        }
        User newUser = new User(
                null, dtoUser.getUsername(), dtoUser.getPassword(), true, null
        );

        List<Authority> authorityList = authoritiesFromString(dtoUser.getAuthorities());
        newUser.setAuthorities(authorityList);

        return addUser(newUser);
    }

    @Override
    public User addUser(User user) {

        User userFound = userRepository.findByUsername(user.getUsername());
        if(userFound != null){
            throw new KeyRepeatedDataExeception("Username: "+ user.getUsername()+" is already registeted");
        }

        //Paso 1: Validar si el username y el password cumplen con los requisitos: minimo, maximo, tipos carecteres

        //Paso 2: Encriptar el password
        user.setPassword( new BCryptPasswordEncoder().encode(user.getPassword())  );

        //Paso 3: Actualizar los campos adicionales segun tu logica de negocio
        user.setEnabled(true);

        return userRepository.save(user);
    }


}
