package wspa.vehicle.model.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import wspa.vehicle.model.User;
import wspa.vehicle.model.UserRole;
import wspa.vehicle.model.dto.UserDto;
import wspa.vehicle.repositories.UserRoleRepository;
@Service
public class UserMapper {

    private UserRoleRepository userRoleRepository;
    private static final String DEFAULT_ROLE=  "ROLE_USER";
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserMapper( UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public UserDto userDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        String passwordHash = passwordEncoder.encode(user.getPassword());
        dto.setPassword(passwordHash);
        dto.setRoles(user.getRoles());
        return dto;
    }
    public User toEntity(UserDto user) {
        User entity = new User();
        entity.setId(user.getId());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        String passwordHash = passwordEncoder.encode(user.getPassword());
        entity.setPassword(passwordHash);
        UserRole defaultRole = userRoleRepository.findByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        entity.setRoles(user.getRoles());
        return entity;
    }

}
