package wspa.vehicle.services;

import org.springframework.stereotype.Service;
import wspa.vehicle.exceptions.*;
import wspa.vehicle.model.Car;
import wspa.vehicle.model.Order;
import wspa.vehicle.model.User;
import wspa.vehicle.model.dto.OrderDto;
import wspa.vehicle.model.dto.UserOrderDto;
import wspa.vehicle.model.mappers.OrderMapper;
import wspa.vehicle.model.mappers.UserOrderMapper;
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
    private OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public Optional<OrderDto> findById (Long id)
    {
        return orderRepository.findById(id).map(orderMapper::toDto);
    }

    public List<UserOrderDto> getAllActiveOrders()
    {
     return orderRepository.findAll()
             .stream()
             .filter(o -> o.getEnd() == null)
             .map(UserOrderMapper::toDto)
             .collect(Collectors.toList());
    }

    public List<UserOrderDto> getAll()
    {
        return orderRepository.findAll()
                .stream()
                .map(UserOrderMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<UserOrderDto> getByUserName (String text)
    {
        return orderRepository.findByUser_LastName(text)
                .stream()
                .map(UserOrderMapper::toDto)
                .collect(Collectors.toList());
    }

    public OrderDto createOrder(OrderDto orderDto)
    {
        Optional<Order> activeOrderByCar  = orderRepository.findByCar_IdAndEndIsNull(orderDto.getCarId());
        activeOrderByCar.ifPresent(u ->{
            throw new ActiveOrderException();
        });

        return mapAndSave(orderDto);
    }
    public OrderDto updateOrder(OrderDto orderDto)
    {
        Optional<Order> orderById = orderRepository.findById(orderDto.getId());
        if (orderById == null)
            throw new OrderNotFoundException();

        return mapAndSave(orderDto);
    }
    @Transactional
    public LocalDateTime finishOrder (Long orderId)
    {
        Optional<Order> order = orderRepository.findById(orderId);
        Order orderEntity = order.orElseThrow(OrderNotFoundException::new);
        if (orderEntity.getEnd()!=null)
            throw new OrderAlreadyFinishedException();
        if (orderEntity.getPrice() <= 0)
            throw new InvalidPriceException();
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
