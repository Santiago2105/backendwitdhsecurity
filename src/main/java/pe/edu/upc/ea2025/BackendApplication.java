package pe.edu.upc.ea2025;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.edu.upc.ea2025.sacjEntities.Authority;
import pe.edu.upc.ea2025.sacjEntities.Equipment;
import pe.edu.upc.ea2025.sacjEntities.Rent;
import pe.edu.upc.ea2025.sacjEntities.User;
import pe.edu.upc.ea2025.sacjRepositories.EquipmentRepository;
import pe.edu.upc.ea2025.sacjRepositories.RentRepository;
import pe.edu.upc.ea2025.sacjServices.AuthorityService;
import pe.edu.upc.ea2025.sacjServices.UserService;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner startConfiguration(
            EquipmentRepository equipmentRepository,
            RentRepository rentRepository,
            AuthorityService authorityService,
            UserService userService

    ){
        return args -> {
            //PARTE DE SEGURIDAD
            //CREANDO LOS ROLES O NIVELES DE SEGURIDAD ALGO ASI
            Authority authority1 = authorityService.addAuthority(new Authority("ROLE_ADMIN"));
            Authority authority2 = authorityService.addAuthority(new Authority("ROLE_PUBLICO"));
            Authority authority3 = authorityService.addAuthority(new Authority("ROLE_USER"));


            //CREANDO USUARIOS CON SUS ROLES
            userService.addUser(new User(null, "santiago","2105",true,
                    List.of(authority1, authority2)));

            userService.addUser(new User(null, "alessandro","valeria",true,
                    List.of(authority2)));

            userService.addUser(new User(null, "aixa","ajinomen",true,
                    List.of(authority3)));

            //PARTE DE SEGURIDAD
            // ================================
            // 1. Crear Equipments
            // ================================
            Equipment equipment1 = equipmentRepository.save(new Equipment(null,"equipment1", "switch",
                    LocalDate.of(2025,2,15),200.00,null));
            Equipment equipment2 = equipmentRepository.save(new Equipment(null,"equipment2", "router",
                    LocalDate.of(2025,5,15),500.00,null));



            Rent rent1 = rentRepository.save(new Rent(null, "pepito1", LocalDate.of(2025,2,15), 1, true, equipment1));
            Rent rent2 = rentRepository.save(new Rent(null, "juanito23",  LocalDate.of(2025,2,16), 1, true, equipment1));
            Rent rent3 = rentRepository.save(new Rent(null, "elmatias89",  LocalDate.of(2025,2,18), 1, true, equipment1));
            Rent rent4 = rentRepository.save(new Rent(null, "loconaso18",  LocalDate.of(2025,2,19), 5, true, equipment1));
            Rent rent5 = rentRepository.save(new Rent(null, "Alessandro",   LocalDate.of(2025,2,23), 1, true, equipment1));
            //hasta aca se estarian cargando 5 barriles a yacimiento 1
            Rent rent6 = rentRepository.save(new Rent(null, "aicsa", LocalDate.of(2025,5,20), 1, true, equipment2));


            System.out.println("=== Equipos Registrados ===");
            equipmentRepository.findAll().forEach(System.out::println);

            System.out.println("=== Rentas Registradas ===");
            rentRepository.findAll().forEach(System.out::println);


        };
    };
}
