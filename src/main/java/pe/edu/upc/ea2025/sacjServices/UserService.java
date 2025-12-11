package pe.edu.upc.ea2025.sacjServices;

import pe.edu.upc.ea2025.sacjDtos.DTOUser;
import pe.edu.upc.ea2025.sacjEntities.User;

public interface UserService {

    public User findById (Long id);
    public DTOUser findByIdDTO (Long id);
    public User findByUsername(String username);

    public User addUser(DTOUser dtoUser);
    public User addUser(User user);
    //aqui se va a usar un DTO



}
