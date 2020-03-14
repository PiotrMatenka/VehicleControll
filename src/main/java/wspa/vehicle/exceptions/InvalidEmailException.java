package wspa.vehicle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Błędny adres email")
public class InvalidEmailException extends RuntimeException {
}
