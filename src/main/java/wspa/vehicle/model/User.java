package wspa.vehicle.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
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
    @NotNull(message = "Pole nie może być puste")
    @Pattern(regexp = "[\\d]{9}", message = "Numer musi być 9 cyfrowy")
    private String phoneNumber;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Car> cars = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Order> orders = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "role_user",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<UserRole> roles = new HashSet<>();

}
