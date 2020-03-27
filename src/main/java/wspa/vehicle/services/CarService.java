package wspa.vehicle.services;

import org.springframework.stereotype.Service;
import wspa.vehicle.exceptions.DuplicateVinException;
import wspa.vehicle.model.Car;
import wspa.vehicle.model.dto.CarDto;
import wspa.vehicle.model.mappers.CarMapper;
import wspa.vehicle.repositories.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class CarService {
    private CarRepository carRepository;
    private CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public CarDto saveCar(CarDto carDto)
    {
        Optional<Car> carById = carRepository.findById(carDto.getId());
        carById.ifPresent(c -> {
            if (c.getVin().equals(carDto.getVin()))
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


}
