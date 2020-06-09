package wspa.vehicle.services;

import org.springframework.stereotype.Service;
import wspa.vehicle.exceptions.DuplicateEmailException;
import wspa.vehicle.exceptions.UserNotFoundException;
import wspa.vehicle.model.User;
import wspa.vehicle.model.UserRole;
import wspa.vehicle.model.dto.CarDto;
import wspa.vehicle.model.dto.UserDto;
import wspa.vehicle.model.dto.UserOrderDto;
import wspa.vehicle.model.mappers.CarMapper;
import wspa.vehicle.model.mappers.UserMapper;
import wspa.vehicle.model.mappers.UserOrderMapper;
import wspa.vehicle.repositories.CarRepository;
import wspa.vehicle.repositories.UserRepository;
import wspa.vehicle.repositories.UserRoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private CarRepository carRepository;
    private CarMapper carMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, CarRepository carRepository, CarMapper carMapper)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public List<UserDto> findAll()
    {
        return userRepository.findAll().stream().map(userMapper::userDto).collect(Collectors.toList());
    }

    public List<UserDto> findByLastName (String text)
    {
        return userRepository.findByLastName(text)
                .stream()
                .map(userMapper::userDto)
                .collect(Collectors.toList());
    }

    public UserDto saveUser (UserDto user)
    {
        User userByEmail = userRepository.findByEmail(user.getEmail());
        if (userByEmail !=null)
        {
            if (user.getEmail().equals(userByEmail.getEmail()))
                throw new DuplicateEmailException();
        }
        return mapAndSave(user);
    }

    public UserDto updateUser (UserDto user)
    {
        User userByEmail = userRepository.findByEmail(user.getEmail());
        if (userByEmail !=null && !userByEmail.getId().equals(user.getId()))
            throw new DuplicateEmailException();
        return mapAndSave(user);
    }

    public Optional<UserDto> findById(Long id)
    {
        return userRepository.findById(id)
                .map(userMapper::userDto);
    }


    public List<UserOrderDto> getUserOrders(Long userId)
    {
        return userRepository.findById(userId)
                .map(User::getOrders)
                .orElseThrow(UserNotFoundException::new)
                .stream()
                .filter(u -> u.getEnd() == null)
                .map(UserOrderMapper::toDto)
                .collect(Collectors.toList());
    }

    private UserDto mapAndSave(UserDto user)
    {
        User userEntity = userMapper.toEntity(user);
        User savedUser = userRepository.save(userEntity);
        return userMapper.userDto(savedUser);
    }
    public Set<CarDto> getCars (Long id )
    {
        Optional<User>user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException();
        else
            return carRepository.findAllByUser_Id(id).stream()
                    .map(carMapper::carDto).collect(Collectors.toSet());
    }

    public Optional<UserRole> getAdminRole(Long id)
    {
      Set<UserRole> userRoles = new HashSet<>(userRepository.findById(id)
              .map(User::getRoles)
              .orElseThrow(UserNotFoundException::new));
      UserRole userRole = new UserRole();
      for (UserRole r:userRoles)
          if (r.getRole().equals("ADMIN"))
              userRole = r;
      return Optional.of(userRole);
    }
}
