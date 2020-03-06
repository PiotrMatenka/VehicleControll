package wspa.vehicle.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import wspa.vehicle.model.UserRole;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<UserRole> roles = new HashSet<>();
}
