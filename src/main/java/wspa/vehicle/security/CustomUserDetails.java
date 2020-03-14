package wspa.vehicle.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import wspa.vehicle.model.User;
import wspa.vehicle.model.UserRole;
import wspa.vehicle.model.dto.UserDto;
import wspa.vehicle.model.mappers.UserMapper;
import wspa.vehicle.repositories.UserRepository;
import wspa.vehicle.services.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class CustomUserDetails implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        org.springframework.security.core.userdetails.User userDetails;
        userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), convertAuthorities(user.getRoles()));
        return userDetails;
    }
    private Set<GrantedAuthority> convertAuthorities(Set<UserRole>userRoles)
    {
        Set<GrantedAuthority>authorities = new HashSet<>();
        for (UserRole ur: userRoles)
        {
            authorities.add(new SimpleGrantedAuthority(ur.getRole()));
        }
        return authorities;
    }
}
