package wspa.vehicle.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import wspa.vehicle.model.User;
import wspa.vehicle.model.UserRole;

import wspa.vehicle.repositories.UserRepository;


import javax.transaction.Transactional;
import java.util.HashSet;

import java.util.Set;

@Transactional
public class CustomUserDetails implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetails.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("User not found");

        return new UserPrincipal(user);
    }
}
