package wspa.vehicle.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Pole nie może być puste")
    private String firstName;
    @NotNull(message = "Pole nie może być puste")
    private String lastName;
    @Column(unique = true)
    @Email(message = "zły email")
    @NotNull(message = "Pole nie może być puste")
    private String email;
    @NotNull(message = "Pole nie może być puste")
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Car> cars = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<UserRole> roles = new HashSet<>();

}
