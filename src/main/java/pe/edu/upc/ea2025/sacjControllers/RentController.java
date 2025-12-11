package pe.edu.upc.ea2025.sacjControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ea2025.sacjDtos.DTORentCreate;
import pe.edu.upc.ea2025.sacjEntities.Rent;
import pe.edu.upc.ea2025.sacjServices.RentService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/cjsa")
public class RentController {
    @Autowired
    RentService rentService;

    @GetMapping("/rents")
    public ResponseEntity<List<Rent>> listAll(){
        List<Rent> rents = rentService.listAll();
        return new ResponseEntity<>(rents, HttpStatus.OK);
    }

    @PostMapping("/rents/insert")
    public ResponseEntity<Rent> add(@RequestBody DTORentCreate DTOrent) {
        if (DTOrent.getClientName()==null || DTOrent.getRentStartDate() == null || DTOrent.getRentDays() == null ||
                DTOrent.getValid() == null || DTOrent.getEquipmentId() == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Rent newRent = rentService.add(DTOrent);
        return new ResponseEntity<>(newRent, HttpStatus.CREATED);
    }

    @GetMapping("/rents/{id}")
    public ResponseEntity<Rent> findById(@PathVariable("id") Long id){
        Rent foundRent = rentService.findById(id);
        if(foundRent == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundRent, HttpStatus.OK);
    }


//    //HU3
//    @GetMapping("/pozo/HU3/{id}")
//    public ResponseEntity<List<sacjPozo>> listarPozosPorYacimiento(@PathVariable("id") Long yacimientoId) {
//
//        // Verificación opcional pero recomendada: si el yacimiento no existe,
//        // JPA lanzará una excepción que será atrapada por el GlobalExceptionHandler.
//        // O podrías lanzar tu propia excepción aquí.
//        // Por simplicidad para la HU, podemos confiar en el handler global.
//
//        List<sacjPozo> listaPozos = pozoService.findBySacjyacimientoId(yacimientoId);
//        // Si no se encuentran pozos para un yacimiento que sí existe,
//        // simplemente se devuelve una lista vacía, lo cual es correcto.
//        return new ResponseEntity<>(listaPozos, HttpStatus.OK);
//
//    }

}
