package wspa.vehicle.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CarDto {
    private Long id;
    private String producer;
    private String model;
    private int yearOfProduction;
    private String vin;
    private String registration;
    private Long userId;
}
