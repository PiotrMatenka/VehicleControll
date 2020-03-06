package wspa.vehicle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wspa.vehicle.model.Car;
import wspa.vehicle.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByUser_Id(Long id);
    Optional<Car> findByUser_Id(Long id);

}
