package wspa.vehicle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Zlecenie o podanym id nie istnieje")
public class OrderNotFoundException extends RuntimeException {
}
