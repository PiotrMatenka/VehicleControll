package wspa.vehicle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Samochód o podanym vin istnieje w bazie")
public class DuplicateVinException extends RuntimeException {
}
