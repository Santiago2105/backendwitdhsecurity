package pe.edu.upc.ea2025.sacjRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.ea2025.sacjDtos.DTOEquipmentReport;
import pe.edu.upc.ea2025.sacjEntities.Equipment;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<EquipmentRepository> findByName(String name);
    //List<Equipment> findByTypeEquipment(String type);

//    @Query("SELECT new pe.edu.upc.backend.dtos.DTOYacimientoReporte(y.name, SUM(p.scdailyBarrels)) " +
//            "FROM Equipment y JOIN y.Renta p " +
//            "GROUP BY y.name")
    //List<DTOEquipmentReport> HU4();

}
