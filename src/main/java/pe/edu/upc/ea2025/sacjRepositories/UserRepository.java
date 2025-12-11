package pe.edu.upc.ea2025.sacjRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.ea2025.sacjEntities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

}
