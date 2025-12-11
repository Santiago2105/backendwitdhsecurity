package pe.edu.upc.ea2025.sacjDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DTORentCreate {

    private String clientName;
    private LocalDate rentStartDate;
    private Integer rentDays;
    private Boolean valid;
    private Long equipmentId;

}
