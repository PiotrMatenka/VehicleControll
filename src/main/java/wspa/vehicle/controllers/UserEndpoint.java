package wspa.vehicle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wspa.vehicle.exceptions.EmptyFieldsException;
import wspa.vehicle.exceptions.InvalidEmailException;
import wspa.vehicle.model.User;
import wspa.vehicle.model.dto.CarDto;
import wspa.vehicle.model.dto.UserDto;
import wspa.vehicle.model.dto.UserOrderDto;
import wspa.vehicle.services.CarService;
import wspa.vehicle.services.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserEndpoint {
    private UserService userService;
    @Autowired
    public UserEndpoint(UserService userService)
    {
        this.userService = userService;
    }
    @GetMapping("")
    public List<UserDto> getAll()
    {
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id)
    {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/orders")
    public List<UserOrderDto> getUserOrders (@PathVariable Long id)
    {
        return userService.getUserOrders(id);
    }

    @GetMapping("/{id}/cars")
    public List<CarDto> getCars(@PathVariable Long id)
    {
        return userService.getCars(id);
    }

    @PostMapping("")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto user, BindingResult result)
    {
        if (user.getId() != null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywany obiekt nie może mieć ustawionego Id");
            if (result.hasErrors())
             {
                 List<ObjectError> errors = result.getAllErrors();
                 errors.forEach(err -> System.out.println(err.getDefaultMessage()));
                 return ResponseEntity.status(HttpStatus.CONFLICT).build();
             }

            UserDto savedUser = userService.saveUser(user);
            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
            return ResponseEntity.created(location).body(savedUser);}
}
