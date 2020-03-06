package wspa.vehicle.model.mappers;

import wspa.vehicle.model.Car;
import wspa.vehicle.model.Order;
import wspa.vehicle.model.User;
import wspa.vehicle.model.dto.OrderDto;

public class OrderMapper {
    public static OrderDto toDto (Order order)
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
}
