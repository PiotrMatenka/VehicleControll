package wspa.vehicle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wspa.vehicle.model.UserRole;
import wspa.vehicle.model.dto.CarDto;
import wspa.vehicle.model.dto.UserDto;
import wspa.vehicle.model.dto.UserOrderDto;
import wspa.vehicle.services.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public List<UserDto> getAll(@RequestParam (required = false) String text)
    {
        if (text != null)
            return userService.findByLastName(text);
        else
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
    public Set<CarDto> getCars(@PathVariable Long id)
    {
        return userService.getCars(id);
    }

    @GetMapping(value = "/{id}/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<UserRole>> getUserRole(@PathVariable Long id)
    {
        return ResponseEntity.ok(userService.getAdminRole(id));
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
            return ResponseEntity.created(location).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update (@PathVariable Long id, @RequestBody UserDto user)
    {
        if (!id.equals(user.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aktualizowany obiekt musi mieć id zgodne z id w ścieżce zasobu");
        UserDto updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }
}
