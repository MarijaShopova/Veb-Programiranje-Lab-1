package mk.finki.ukim.mk.lab.model.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PizzaRequest {

    @NotNull
    private String name;

    private String description;

    private Boolean veggie;

    private List<String> ingredients;
}
