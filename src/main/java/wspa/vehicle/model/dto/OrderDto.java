package wspa.vehicle.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
public class OrderDto {
    private Long id;
    private String description;
    private double price;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long userId;
    private Long carId;

}
