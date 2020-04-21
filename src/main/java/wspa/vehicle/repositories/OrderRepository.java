package wspa.vehicle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wspa.vehicle.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUser_IdAndEndIsNull(Long userId);
    @Query("select o from Order o where lower(o.user.lastName) like lower(concat('%', :text, '%')) ")
    List<Order> findByUser_LastName(String text);
    Optional <Order> findByCar_IdAndEndIsNull (Long carId);


}
