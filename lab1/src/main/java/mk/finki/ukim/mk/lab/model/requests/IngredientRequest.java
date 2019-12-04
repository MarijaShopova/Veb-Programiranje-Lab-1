package mk.finki.ukim.mk.lab.model.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IngredientRequest {
    @NotNull
    private String name;

    private Boolean spicy;

    private Float amount;

    private Boolean veggie;
}
