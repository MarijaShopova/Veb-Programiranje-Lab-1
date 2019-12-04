package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PizzaNotVeggieException extends RuntimeException {
    public PizzaNotVeggieException(String id) {
        super(String.format("Pizza %s is not veggie!", id));
    }
}
