package wspa.vehicle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wspa.vehicle.exceptions.EmptyFieldsException;
import wspa.vehicle.model.dto.CarDto;
import wspa.vehicle.model.dto.OrderDto;
import wspa.vehicle.services.CarService;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("api/cars")
public class CarEndpoint {
    private CarService carService;
    @Autowired
    public CarEndpoint(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getById(@PathVariable Long id)
    {
        return carService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   @GetMapping(value = "")
    public List<CarDto> getAllCars()
    {
        return carService.getAllCars();
    }

    @GetMapping("/{id}/orders")
    public List<OrderDto> getCarOrders(@PathVariable Long id)
    {
        return carService.getCarOrders(id);
    }


    @PostMapping(value = "")
    public ResponseEntity<CarDto> saveCar (@RequestBody CarDto car, BindingResult result)
    {
        if (car.getId() != null )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywany obiekt nie może mieć ustawionego Id");
        if (result.hasErrors())
            throw new EmptyFieldsException();
        CarDto savedCar = carService.saveCar(car);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCar.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> update (@PathVariable Long id, @RequestBody CarDto car)
    {
        if (!id.equals(car.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aktualizowany obiekt musi mieć id zgodne z id w ścieżce zasobu");
        CarDto updatedCar = carService.updateCar(car);
        return ResponseEntity.ok(updatedCar);
    }

}
