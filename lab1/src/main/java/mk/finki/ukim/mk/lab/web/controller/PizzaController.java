package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.requests.PizzaRequest;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/pizzas")
public class PizzaController {

    private final PizzaService service;

    public PizzaController(PizzaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pizza> getPizzas(@RequestParam(value = "totalIngredients", required = false) Long totalIngredients) {
        return totalIngredients == null ? this.service.getPizzas() : this.service.getPizzasWithLessTotalIngredients(totalIngredients);
    }

    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable String id) {
        return this.service.findPizzaByName(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza createPizza(@RequestBody @Valid PizzaRequest request) {
        return this.service.createPizza(request);
    }

    @PutMapping("/{id}")
    public Pizza updatePizza(@RequestBody @Valid PizzaRequest request, @PathVariable String id) {
        return this.service.updatePizza(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePizza(@PathVariable String id) {
        this.service.deleteByName(id);
    }

    @GetMapping("/compare")
    public List<Ingredient> getSameIngredients(@RequestParam("pizza1") String pizza1,
                                               @RequestParam("pizza2") String pizza2) {
        return this.service.getSameIngredients(pizza1, pizza2);
    }

    @GetMapping("/spicy_ingredient")
    public List<Pizza> getWithSpicyIngredient() {
        return this.service.findAllWithSpicyIngredient();
    }
}
