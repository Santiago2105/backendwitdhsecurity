package pe.edu.upc.ea2025.sacjEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="rent")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scClientName;
    private LocalDate scRentStartDay;
    private Integer scRentDays;
    private Boolean scValid;

    @ManyToOne
    @JoinColumn(name="equipment_id")
    private Equipment equipment;

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", scClientName='" + scClientName + '\'' +
                ", scRentStartDay=" + scRentStartDay +
                ", scRentDays=" + scRentDays +
                ", scValid=" + scValid +
                ", equipamientoId=" + (equipment != null ? equipment.getId() : "null") +
                '}';
    }
}
