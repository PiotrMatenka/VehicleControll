package wspa.vehicle.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class UserOrderDto {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long carId;
    private String carProducer;
    private String carModel;
    private String description;
}
