package wspa.vehicle.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wspa.vehicle.exceptions.DuplicateEmailException;
import wspa.vehicle.exceptions.EmptyFieldsException;
import wspa.vehicle.exceptions.InvalidEmailException;
import wspa.vehicle.exceptions.UserNotFoundException;
import wspa.vehicle.model.User;
import wspa.vehicle.model.dto.CarDto;
import wspa.vehicle.model.dto.UserDto;
import wspa.vehicle.model.dto.UserOrderDto;
import wspa.vehicle.model.mappers.CarMapper;
import wspa.vehicle.model.mappers.UserMapper;
import wspa.vehicle.model.mappers.UserOrderMapper;
import wspa.vehicle.repositories.CarRepository;
import wspa.vehicle.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
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

    public Optional<UserDto> findById(Long id)
    {
        return userRepository.findById(id).map(userMapper::userDto);
    }

    public List<UserOrderDto> getUserOrders(Long userId)
    {
        return userRepository.findById(userId)
                .map(User::getOrders)
                .orElseThrow(UserNotFoundException::new)
                .stream()
                .map(UserOrderMapper::toDto)
                .collect(Collectors.toList());
    }
    private UserDto mapAndSave(UserDto user)
    {
        User userEntity = userMapper.toEntity(user);
        User savedUser = userRepository.save(userEntity);
        return userMapper.userDto(savedUser);
    }
    public List<CarDto> getCars (Long id )
    {
        Optional<User>user = userRepository.findById(id);
        if (user == null)
            throw new UserNotFoundException();
        else
            return carRepository.findAllByUser_Id(id).stream().map(carMapper::carDto).collect(Collectors.toList());
    }




}
