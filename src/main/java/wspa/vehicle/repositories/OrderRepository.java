package wspa.vehicle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wspa.vehicle.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUser_IdAndEndIsNull(Long userId);

}
