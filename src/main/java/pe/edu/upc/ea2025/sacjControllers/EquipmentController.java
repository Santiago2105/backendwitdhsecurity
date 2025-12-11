package pe.edu.upc.ea2025.sacjControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ea2025.sacjDtos.DTOEquipmentReport;
import pe.edu.upc.ea2025.sacjEntities.Equipment;
import pe.edu.upc.ea2025.sacjServices.EquipmentService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/cjsa")
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;


    @GetMapping("/equipment")
    public ResponseEntity<List<Equipment>> listAll(){
        List<Equipment> equipments = equipmentService.listAll();
        return new ResponseEntity<>(equipments, HttpStatus.OK);
    }

    @PostMapping("/equipment/insert")
    public ResponseEntity<Equipment> add(@RequestBody Equipment equipment) {
        if (equipment.getName() == null || equipment.getScType() == null || equipment.getScFabricationDate() == null || equipment.getScPricePerDay() == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Equipment newEquipment = equipmentService.add(equipment);
        return new ResponseEntity<>(newEquipment, HttpStatus.CREATED);
    }

    @GetMapping("/equipment/{id}")
    public ResponseEntity<Equipment> findById(@PathVariable("id") Long id){
        Equipment foundEquipment = equipmentService.findById(id);
        return new ResponseEntity<>(foundEquipment, HttpStatus.OK);
    }

//    @GetMapping("/equipment/disponibles/{type}")
//    public ResponseEntity<Equipment> findbyTypeEquipment(@PathVariable("scType") String type){
//        Equipment foundEquipment = (Equipment) equipmentService.findByTypeEquipment(type);
//        return new ResponseEntity<>(foundEquipment, HttpStatus.OK);
//    }

//    @GetMapping("/equipment/reportes")
//    public ResponseEntity<List<DTOEquipmentReport>> obtenerReporte() {
//        List<DTOEquipmentReport> reporte = equipmentService.obtenerReporteDeEquipemnt();
//        return new ResponseEntity<>(reporte, HttpStatus.OK);
//    }

}
