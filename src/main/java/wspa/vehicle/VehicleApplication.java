package wspa.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wspa.vehicle.model.Car;

@SpringBootApplication

public class VehicleApplication {

    public static void main(String[] args) {

        SpringApplication.run(VehicleApplication.class, args);


    }

}
