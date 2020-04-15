package wspa.vehicle.services;

import org.springframework.stereotype.Service;
import wspa.vehicle.exceptions.CarNotFoundException;
import wspa.vehicle.exceptions.DuplicateVinException;
import wspa.vehicle.model.Car;
import wspa.vehicle.model.dto.CarDto;
import wspa.vehicle.model.dto.OrderDto;
import wspa.vehicle.model.mappers.CarMapper;
import wspa.vehicle.model.mappers.OrderMapper;
import wspa.vehicle.repositories.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class CarService {
    private CarRepository carRepository;
    private CarMapper carMapper;
    private OrderMapper orderMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper, OrderMapper orderMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.orderMapper = orderMapper;
    }

    public CarDto saveCar(CarDto carDto)
    {
        Optional<Car> carByVin = carRepository.findByVin(carDto.getVin());
        carByVin.ifPresent(c ->{
           throw new DuplicateVinException();
       });
       return mapAndSave(carDto);
    }

    public CarDto updateCar (CarDto carDto)
    {
        Optional<Car> carByVin = carRepository.findByVin(carDto.getVin());
        carByVin.ifPresent(c -> {
            if (!c.getId().equals(carDto.getId()))
                throw new DuplicateVinException();
        });
        return mapAndSave(carDto);
    }

    private CarDto mapAndSave(CarDto car)
    {
        Car entity = carMapper.toEntity(car);
        Car savedCar = carRepository.save(entity);
        return carMapper.carDto(savedCar);
    }



    public Optional<CarDto> findById(Long id)
    {
        return carRepository.findById(id).map(carMapper::carDto);
    }
    public List<CarDto> getAllCars()
    {
        return carRepository.findAll().stream().map(carMapper::carDto).collect(Collectors.toList());
    }
    public List<OrderDto> getCarOrders(Long carId)
    {
        return carRepository.findById(carId)
                .map(Car::getOrders)
                .orElseThrow(CarNotFoundException::new)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }


}
