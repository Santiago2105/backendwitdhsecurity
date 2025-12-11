package pe.edu.upc.ea2025.sacjServiceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ea2025.sacjDtos.DTOEquipmentReport;
import pe.edu.upc.ea2025.sacjEntities.Equipment;
import pe.edu.upc.ea2025.sacjRepositories.EquipmentRepository;
import pe.edu.upc.ea2025.sacjServices.EquipmentService;

import java.util.List;

@Service
public class EquipmentServiceImpl  implements EquipmentService {

    @Autowired
    EquipmentRepository equipmentRepository;

    @Override
    public Equipment add(Equipment equipment) {
        if (equipment.getName()==null || equipment.getName().isBlank()) {
            return null;
        }
        //Validar si el nombre ya esta registrado
        if (!equipmentRepository.findByName(equipment.getName()).isEmpty()) {
            System.out.println(equipmentRepository.findByName(equipment.getName()));
            return null;

        }
        return equipmentRepository.save(equipment);
    }


    @Override
    public Equipment findById(Long id) {
        return equipmentRepository.findById(id).orElse(null);
    }

//    @Override
//    public List<Equipment> findByTypeEquipment(String type) {
//        return equipmentRepository.findByTypeEquipment(type);
//    }

    @Override
    public List<Equipment> listAll() {
        return equipmentRepository.findAll();
    }

//    @Override
//    public List<DTOEquipmentReport> obtenerReporteDeEquipemnt() {
//        return equipmentRepository.HU4();
//    }
}
