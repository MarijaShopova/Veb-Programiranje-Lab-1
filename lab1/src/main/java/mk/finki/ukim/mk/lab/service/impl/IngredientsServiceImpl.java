package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.ExceededLimitSpicyIngredients;
import mk.finki.ukim.mk.lab.model.exceptions.IngredientAlreadyExistsException;
import mk.finki.ukim.mk.lab.model.exceptions.IngredientNotFoundException;
import mk.finki.ukim.mk.lab.model.requests.IngredientRequest;
import mk.finki.ukim.mk.lab.repository.IngredientsRepository;
import mk.finki.ukim.mk.lab.service.IngredientsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    private final IngredientsRepository repository;

    public IngredientsServiceImpl(IngredientsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ingredient save(IngredientRequest request) {
        Ingredient ingredient = new Ingredient(0L, request.getName(), request.getSpicy(), request.getAmount(), request.getVeggie());
        if (this.repository.findByName(request.getName()).isPresent()) {
            throw new IngredientAlreadyExistsException(request.getName());
        }
        if (this.getSpicy().size() == 3 && ingredient.getSpicy()) {
            throw new ExceededLimitSpicyIngredients();
        }
        return this.repository.save(ingredient);
    }

    @Override
    public Ingredient update(String name, IngredientRequest request) {
        Ingredient ingredient = this.findByName(name);
        if (!ingredient.getSpicy() && request.getSpicy() && this.getSpicy().size() == 3) {
            throw new ExceededLimitSpicyIngredients();
        }
        ingredient.setName(request.getName());
        ingredient.setAmount(request.getAmount());
        ingredient.setSpicy(request.getSpicy());
        ingredient.setVeggie(request.getVeggie());
        return this.repository.save(ingredient);
    }

    @Transactional
    @Override
    public void delete(String name) {
        this.repository.deleteByName(name);
    }

    @Override
    public Page<Ingredient> getPaged(Boolean spicy, Pageable pageable) {
        if (spicy != null) {
            return this.repository.findAllBySpicy(spicy, pageable);
        }
        return this.repository.findAll(pageable);
    }

    @Override
    public Ingredient findByName(String name) {
        return this.repository.findByName(name).orElseThrow(() -> new IngredientNotFoundException(name));
    }

    @Override
    public List<Pizza> getPizzas(String name) {
        return this.findByName(name).getPizzas();
    }

    private List<Ingredient> getSpicy() {
        return this.repository.findAllBySpicy(true);
    }
}
