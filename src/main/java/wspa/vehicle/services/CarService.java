package wspa.vehicle.services;

import org.springframework.stereotype.Service;
import wspa.vehicle.exceptions.DuplicateRegistrationException;
import wspa.vehicle.exceptions.UserNotFoundException;
import wspa.vehicle.model.Car;
import wspa.vehicle.model.User;
import wspa.vehicle.model.dto.CarDto;
import wspa.vehicle.model.mappers.CarMapper;
import wspa.vehicle.repositories.CarRepository;
import wspa.vehicle.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class CarService {
    private CarRepository carRepository;
    private UserRepository userRepository;
    private CarMapper carMapper;

    public CarService(CarRepository carRepository, UserRepository userRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.carMapper = carMapper;
    }

    public CarDto saveCar(CarDto carDto)
    {
        Optional<Car> carByRegistration = carRepository.findById(carDto.getId());
        carByRegistration.ifPresent(c -> {
            if (c.getRegistration().equals(carDto.getRegistration()))
                throw new DuplicateRegistrationException();
        });
       return mapAndSave(carDto);
    }

    private CarDto mapAndSave(CarDto car)
    {
        Car entity = carMapper.toEntity(car);
        Car savedCar = carRepository.save(entity);
        return carMapper.carDto(savedCar);
    }

    public List<CarDto> getUsersCars (Long id )
    {
       Optional<User>user = userRepository.findById(id);
       if (user == null)
           throw new UserNotFoundException();
       else
           return carRepository.findAllByUser_Id(id).stream().map(carMapper::carDto).collect(Collectors.toList());
    }

    public Optional<CarDto> findById(Long id)
    {
        return carRepository.findById(id).map(carMapper::carDto);
    }



}
