package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.requests.IngredientRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IngredientsService {
    Ingredient save(IngredientRequest request);

    Ingredient update(String name, IngredientRequest request);

    void delete(String name);

    Page<Ingredient> getPaged(Boolean spicy, Pageable pageable);

    Ingredient findByName(String name);

    List<Pizza> getPizzas(String name);
}
