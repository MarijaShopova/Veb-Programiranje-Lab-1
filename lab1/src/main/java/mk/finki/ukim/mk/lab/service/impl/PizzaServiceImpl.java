package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.IngredientNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.PizzaAlreadyExistsException;
import mk.finki.ukim.mk.lab.model.exceptions.PizzaNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.PizzaNotVeggieException;
import mk.finki.ukim.mk.lab.model.requests.PizzaRequest;
import mk.finki.ukim.mk.lab.repository.IngredientsRepository;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {
    private final PizzaRepository repository;
    private final IngredientsRepository ingredientsRepository;

    public PizzaServiceImpl(PizzaRepository repository, IngredientsRepository ingredientsRepository) {
        this.repository = repository;
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public List<Pizza> getPizzas() {
        return this.repository.findAll();
    }

    @Override
    public Pizza createPizza(PizzaRequest request) {
        if (this.repository.findByName(request.getName()).isPresent()) {
            throw new PizzaAlreadyExistsException(request.getName());
        }
        Pizza pizza = repository.save(new Pizza(0L, request.getName(), request.getDescription()));
        this.addIngredients(pizza, request.getIngredients());
        pizza.setVeggie(isVeggie(pizza));
        return repository.save(pizza);
    }

    private void addIngredients(Pizza pizza, List<String> ingredients) {
        ingredients.forEach(it -> {
            Ingredient ingredient = ingredientsRepository.findByName(it)
                    .orElseThrow(() -> new IngredientNotFoundException(it));
            pizza.addIngredient(ingredient);
        });
    }

    private Boolean isVeggie(Pizza pizza) {
        return pizza.getIngredients().stream().allMatch(Ingredient::getVeggie);
    }

    @Override
    public Pizza updatePizza(String name, PizzaRequest request) {
        Pizza pizza = this.findPizzaByName(name);
        List<Ingredient> ingredients = new ArrayList<>(pizza.getIngredients());
        ingredients.forEach(pizza::removeIngredient);
        pizza.setName(request.getName());
        pizza.setDescription(request.getDescription());
        this.addIngredients(pizza, request.getIngredients());
        if (request.getVeggie() != null && request.getVeggie() && !isVeggie(pizza)) {
            throw new PizzaNotVeggieException(pizza.getName());
        }
        pizza.setVeggie(isVeggie(pizza));
        return repository.save(pizza);
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        this.repository.deleteByName(name);
    }

    @Override
    public Pizza findPizzaByName(String name) {
        return this.repository.findByName(name).orElseThrow(() -> new PizzaNotFoundException(name));
    }

    @Override
    public List<Pizza> getPizzasWithLessTotalIngredients(Integer totalIngredients) {
        return this.repository.findAll()
                .stream()
                .filter(pizza -> pizza.getIngredients().size() < totalIngredients)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ingredient> getSameIngredients(String name1, String name2) {
        Pizza pizza1 = this.findPizzaByName(name1);
        Pizza pizza2 = this.findPizzaByName(name2);
        return pizza1.getIngredients()
                .stream()
                .filter(ingredient -> pizza2.getIngredients().contains(ingredient))
                .collect(Collectors.toList());
    }
}
