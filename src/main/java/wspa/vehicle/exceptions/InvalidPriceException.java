package wspa.vehicle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Cena musi być wyższa od zera")
public class InvalidPriceException extends RuntimeException {
}
