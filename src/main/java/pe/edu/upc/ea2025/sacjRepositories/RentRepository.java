package pe.edu.upc.ea2025.sacjRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.ea2025.sacjEntities.Equipment;
import pe.edu.upc.ea2025.sacjEntities.Rent;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByEquipmentId(Long id);
}
