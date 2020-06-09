package wspa.vehicle.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String producer;
    @NotEmpty
    private String model;
    @Min(1950)
    @Max(2020)
    private int yearOfProduction;
    @NotEmpty
    private String vin;
    @NotEmpty
    @Column(unique = true)
    private String registration;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    @OneToMany(mappedBy = "car")
    @JsonBackReference
    private List<Order> orders = new ArrayList<>();
}
