package wspa.vehicle.services;

import org.springframework.stereotype.Service;
import wspa.vehicle.exceptions.DuplicateEmailException;
import wspa.vehicle.exceptions.UserNotFoundException;
import wspa.vehicle.model.User;
import wspa.vehicle.model.dto.UserDto;
import wspa.vehicle.model.dto.UserOrderDto;
import wspa.vehicle.model.mappers.UserMapper;
import wspa.vehicle.model.mappers.UserOrderMapper;
import wspa.vehicle.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
    public UserDto findByEmail(String email)
    {
        User user = userRepository.findByEmail(email);
        return userMapper.userDto(user);
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



}
