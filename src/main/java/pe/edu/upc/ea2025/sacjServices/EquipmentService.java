package pe.edu.upc.ea2025.sacjServices;

import pe.edu.upc.ea2025.sacjDtos.DTOEquipmentReport;
import pe.edu.upc.ea2025.sacjEntities.Equipment;

import java.util.List;

public interface EquipmentService {
    public Equipment add(Equipment equipment);

    public Equipment findById(Long id);

    //public List<Equipment> findByTypeEquipment(String type);

    public List<Equipment> listAll();

    //List<DTOEquipmentReport> obtenerReporteDeEquipemnt();
}
