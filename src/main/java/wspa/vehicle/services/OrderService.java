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
    private OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, CarRepository carRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.orderMapper = orderMapper;
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


    public List<OrderDto> getAll()
    {
        return orderRepository.findAll().stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    public OrderDto createOrder(OrderDto orderDto)
    {
        Optional<Order> activeOrderByUser  = orderRepository.findByUser_IdAndEndIsNull(orderDto.getUserId());
        activeOrderByUser.ifPresent(u ->{
            throw new ActiveOrderException();
        });

        return mapAndSave(orderDto);
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
    private OrderDto mapAndSave (OrderDto orderDto)
    {
        Order entity = orderMapper.toEntity(orderDto);
        Order savedOrder = orderRepository.save(entity);
        return orderMapper.toDto(savedOrder);
    }

}
