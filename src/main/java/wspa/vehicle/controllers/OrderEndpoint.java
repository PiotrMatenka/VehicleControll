package wspa.vehicle.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wspa.vehicle.exceptions.InvalidOrderException;
import wspa.vehicle.model.dto.OrderDto;
import wspa.vehicle.repositories.OrderRepository;
import wspa.vehicle.services.OrderService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api/orders")
@RestController
public class OrderEndpoint {
    private OrderService orderService;

    public OrderEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/active")
    public List<OrderDto> getAllActiveOrders()
    {
        return orderService.getAllActiveOrders();
    }

    @GetMapping("/archives")
    public List<OrderDto> getAllEndedOrders()
    {
        return orderService.getAllEndedOrders();
    }

    @PostMapping("")
    ResponseEntity<OrderDto> saveOrder (@RequestBody OrderDto orderDto)
    {
        OrderDto savedOrder;
        try {
            savedOrder = orderService.createOrder(orderDto);
        }catch (InvalidOrderException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrder.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedOrder);
    }
    @PostMapping("/{id}/end")
    public ResponseEntity finishOrder(@PathVariable Long id)
    {
        LocalDateTime endTime = orderService.finishOrder(id);
        return ResponseEntity.accepted().body(endTime);
    }
}
