package wspa.vehicle.services;

import org.springframework.stereotype.Service;
import wspa.vehicle.exceptions.ActiveOrderException;
import wspa.vehicle.exceptions.InvalidOrderException;
import wspa.vehicle.exceptions.OrderAlreadyFinishedException;
import wspa.vehicle.exceptions.OrderNotFoundException;
import wspa.vehicle.model.Car;
import wspa.vehicle.model.Order;
import wspa.vehicle.model.User;
import wspa.vehicle.model.dto.OrderDto;
import wspa.vehicle.model.mappers.OrderMapper;
import wspa.vehicle.repositories.CarRepository;
import wspa.vehicle.repositories.OrderRepository;
import wspa.vehicle.repositories.UserRepository;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private CarRepository carRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, CarRepository carRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    public List<OrderDto> getAllActiveOrders()
    {
        List<OrderDto> orders = getAll();
        List<OrderDto> activeOrders = new ArrayList<>();
        orders.forEach(orderDto -> {
            if (orderDto.getEnd() ==null)
                activeOrders.add(orderDto);
        });
        return activeOrders;
    }
    public List<OrderDto> getAllEndedOrders()
    {
        List<OrderDto> orders = getAll();
        List<OrderDto> endedOrders = new ArrayList<>();
        orders.forEach(orderDto -> {
            if (orderDto.getEnd() !=null)
                endedOrders.add(orderDto);
        });
        return endedOrders;
    }


    private List<OrderDto> getAll()
    {
        return orderRepository.findAll().stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    public OrderDto createOrder(OrderDto orderDto)
    {
        Optional<Order> activeOrderByUser  = orderRepository.findByUser_IdAndEndIsNull(orderDto.getUserId());
        activeOrderByUser.ifPresent(u ->{
            throw new ActiveOrderException();
        });
        Optional<User>user = userRepository.findById(orderDto.getUserId());
        Optional<Car> car = carRepository.findByUser_Id(orderDto.getUserId());
        Order order = new Order();
        Long userId = orderDto.getUserId();
        Long carId = orderDto.getCarId();
        order.setUser(user.orElseThrow(()->
            new InvalidOrderException("Brak użytkownika o id: "+userId)
        ));
        order.setCar(car.orElseThrow(()->
            new InvalidOrderException("Brak samochodu o id: "+carId)
        ));
        order.setStart(LocalDateTime.now());
        return OrderMapper.toDto(orderRepository.save(order));
    }

    @Transactional
    public LocalDateTime finishOrder (Long orderId)
    {
        Optional<Order> order = orderRepository.findById(orderId);
        Order orderEntity = order.orElseThrow(OrderNotFoundException::new);
        if (orderEntity.getEnd()!=null)
            throw new OrderAlreadyFinishedException();
        else
            orderEntity.setEnd(LocalDateTime.now());
        return orderEntity.getEnd();
    }

}
