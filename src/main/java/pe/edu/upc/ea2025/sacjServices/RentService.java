package pe.edu.upc.ea2025.sacjServices;

import pe.edu.upc.ea2025.sacjDtos.DTORentCreate;
import pe.edu.upc.ea2025.sacjEntities.Rent;

import java.util.List;

public interface RentService {
    public Rent add(DTORentCreate DTORent);

    public Rent findById(Long id);

    public List<Rent> listAll();

    List<Rent> findByEquipmentId(Long equipment_id);

}
