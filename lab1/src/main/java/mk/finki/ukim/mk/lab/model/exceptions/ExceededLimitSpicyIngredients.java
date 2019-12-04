package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceededLimitSpicyIngredients extends RuntimeException {
    public ExceededLimitSpicyIngredients() {
        super("The limit for spicy ingredients on menu is 3!");
    }
}
