package wspa.vehicle.model.mappers;

import wspa.vehicle.model.Car;
import wspa.vehicle.model.Order;
import wspa.vehicle.model.dto.UserOrderDto;

public class UserOrderMapper {
    public static UserOrderDto toDto (Order order)
    {
        UserOrderDto userOrderDto = new UserOrderDto();
        userOrderDto.setId(order.getId());
        userOrderDto.setStart(order.getStart());
        userOrderDto.setEnd(order.getEnd());
        userOrderDto.setDescription(order.getDescription());
        Car car = order.getCar();
        userOrderDto.setCarId(car.getId());
        userOrderDto.setCarModel(car.getModel());
        userOrderDto.setCarProducer(car.getProducer());
        return userOrderDto;
    }
}
