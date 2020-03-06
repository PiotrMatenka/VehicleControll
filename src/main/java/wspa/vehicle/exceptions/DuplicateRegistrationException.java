package wspa.vehicle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Samochód o podanej rejestracji istnieje w bazie")
public class DuplicateRegistrationException extends RuntimeException {
}
