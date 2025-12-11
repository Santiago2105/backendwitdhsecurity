package pe.edu.upc.ea2025.sacjRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.ea2025.sacjEntities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    public Authority findByName(String name);

}
