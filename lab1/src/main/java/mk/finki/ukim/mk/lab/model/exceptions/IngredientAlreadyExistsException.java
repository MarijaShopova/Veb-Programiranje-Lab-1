package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IngredientAlreadyExistsException extends RuntimeException {
    public IngredientAlreadyExistsException(String name) {
        super(String.format("Ingredient with name %s already exists", name));
    }
}
