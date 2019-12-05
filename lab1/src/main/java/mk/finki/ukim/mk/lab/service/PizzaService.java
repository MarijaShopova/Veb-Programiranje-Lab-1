package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.requests.PizzaRequest;

import java.util.List;

public interface PizzaService {

    List<Pizza> getPizzas();

    Pizza createPizza(PizzaRequest request);

    Pizza updatePizza(String name, PizzaRequest request);

    void deleteByName(String name);

    Pizza findPizzaByName(String name);

    List<Pizza> getPizzasWithLessTotalIngredients(Long totalIngredients);

    List<Ingredient> getSameIngredients(String pizza1, String pizza2);

    List<Pizza> findAllWithSpicyIngredient();
}
