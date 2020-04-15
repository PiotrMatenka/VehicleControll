package wspa.vehicle.model.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wspa.vehicle.model.Car;
import wspa.vehicle.model.User;
import wspa.vehicle.model.dto.CarDto;
import wspa.vehicle.repositories.UserRepository;

import java.util.Optional;
@Service
public class CarMapper {
    private UserRepository userRepository;

    public CarMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   public CarDto carDto (Car car)
    {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setModel(car.getModel());
        carDto.setProducer(car.getProducer());
        carDto.setYearOfProduction(car.getYearOfProduction());
        carDto.setVin(car.getVin());
        carDto.setRegistration(car.getRegistration());
        if (car.getUser()!= null)
            carDto.setUserId(car.getUser().getId());
        return carDto;
    }

    public Car toEntity(CarDto car)
    {
        Car entity = new Car();
        entity.setId(car.getId());
        entity.setProducer(car.getProducer());
        entity.setModel(car.getModel());
        entity.setYearOfProduction(car.getYearOfProduction());
        entity.setVin(car.getVin());
        entity.setRegistration(car.getRegistration());
        Optional<User> user = userRepository.findById(car.getUserId());
        user.ifPresent(entity::setUser);
        return entity;
    }
}
