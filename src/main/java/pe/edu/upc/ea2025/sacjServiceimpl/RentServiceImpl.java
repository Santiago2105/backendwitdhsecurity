package pe.edu.upc.ea2025.sacjServiceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ea2025.sacjDtos.DTORentCreate;
import pe.edu.upc.ea2025.sacjEntities.Equipment;
import pe.edu.upc.ea2025.sacjEntities.Rent;
import pe.edu.upc.ea2025.sacjRepositories.EquipmentRepository;
import pe.edu.upc.ea2025.sacjRepositories.RentRepository;
import pe.edu.upc.ea2025.sacjServices.RentService;

import java.util.List;

@Service
public class RentServiceImpl implements RentService {
    @Autowired
    RentRepository rentRepository;

    @Autowired
    EquipmentRepository equipmentRepository;

    @Override
    public Rent add(DTORentCreate DTORent) {
        if (DTORent.getClientName()==null || DTORent.getRentStartDate() == null || DTORent.getRentDays() == null) {
            return null;
        }

        Equipment equipmentEncontrado = equipmentRepository.findById(DTORent.getEquipmentId()).orElse(null);

        // 2. Comprueba si el objeto es null.
        if (equipmentEncontrado == null) {
            // Si el yacimiento no existe, no podemos continuar.
            // Lanza una excepción para que tu GlobalExceptionHandler la atrape.
            throw new RuntimeException("Yacimiento con ID " + DTORent.getEquipmentId() + " no encontrado.");
        }

        // 3. Si el código llega hasta aquí, significa que yacimientoEncontrado NO es null
        // y podemos usarlo de forma segura.
        Rent nuevoRent = new Rent();
        //CONVIRTIENDO EL DTOPOZO A UNA ENTIDAD NORMAL POZO PARA QUE ENTRE EN LA LISTA DE POZOS QUE YA TENEMOS
        nuevoRent.setScClientName(DTORent.getClientName());
        nuevoRent.setScRentStartDay(DTORent.getRentStartDate());
        nuevoRent.setScRentDays(DTORent.getRentDays());
        nuevoRent.setScValid(DTORent.getValid());
        // 4. Establece la relación con el yacimiento que encontramos.
        nuevoRent.setEquipment(equipmentEncontrado);

        return rentRepository.save(nuevoRent);
    }

    @Override
    public Rent findById(Long id) {
        return rentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Rent> listAll() {
        return rentRepository.findAll();
    }

    @Override
    public List<Rent> findByEquipmentId(Long equipment_id) {
        return rentRepository.findByEquipmentId(equipment_id);
    }
}
