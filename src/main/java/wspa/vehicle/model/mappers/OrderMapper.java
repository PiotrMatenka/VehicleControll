package wspa.vehicle.model.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wspa.vehicle.model.Car;
import wspa.vehicle.model.Order;
import wspa.vehicle.model.User;
import wspa.vehicle.model.dto.OrderDto;
import wspa.vehicle.repositories.CarRepository;
import wspa.vehicle.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class OrderMapper {
    private UserRepository userRepository;
    private CarRepository carRepository;
    @Autowired
    public OrderMapper(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    public OrderDto toDto (Order order)
    {
        OrderDto dto = new OrderDto();
        dto.setDescription(order.getDescription());
        dto.setPrice(order.getPrice());
        dto.setId(order.getId());
        dto.setStart(order.getStart());
        dto.setEnd(order.getEnd());
        User user = order.getUser();
        dto.setUserId(user.getId());
        Car car = order.getCar();
        dto.setCarId(car.getId());
        return dto;
    }

    public Order toEntity(OrderDto orderDto)
    {
        Order entity = new Order();
        entity.setId(orderDto.getId());
        entity.setDescription(orderDto.getDescription());
        entity.setPrice(orderDto.getPrice());
        entity.setStart(LocalDateTime.now());
        entity.setEnd(null);
        Optional<User> user = userRepository.findById(orderDto.getUserId());
        user.ifPresent(entity::setUser);
        Optional<Car> car = carRepository.findById(orderDto.getCarId());
        car.ifPresent(entity::setCar);
        return entity;
    }


}
