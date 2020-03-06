package wspa.vehicle.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String producer;
    @NotEmpty
    private String model;
    @Min(1970)
    @Max(2020)
    private int yearOfProduction;
    @NotEmpty
    private String vin;
    @NotEmpty
    @Column(unique = true)
    private String registration;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
