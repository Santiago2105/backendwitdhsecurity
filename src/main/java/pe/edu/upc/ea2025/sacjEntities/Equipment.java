package pe.edu.upc.ea2025.sacjEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="equipement")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String scType;
    private LocalDate scFabricationDate;
    private Double scPricePerDay;


    @JsonIgnore
    @OneToMany(mappedBy = "equipment", fetch = FetchType.EAGER)
    private List<Rent> rents;

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", scName='" + name + '\'' +
                ", scType='" + scType + '\'' +
                ", scFabricationDate=" + scFabricationDate +
                ", scPricePerDay=" + scPricePerDay +
                ", rents=" + rents +
                '}';
    }
}
