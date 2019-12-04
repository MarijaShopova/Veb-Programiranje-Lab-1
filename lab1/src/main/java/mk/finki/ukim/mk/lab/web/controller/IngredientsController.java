package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.requests.IngredientRequest;
import mk.finki.ukim.mk.lab.service.IngredientsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientsController {
    private final IngredientsService service;

    public IngredientsController(IngredientsService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Ingredient> getPaged(@PageableDefault() @SortDefault.SortDefaults(
            {@SortDefault(sort = "name", direction = Sort.Direction.ASC)}) Pageable pageable,
                                     @RequestParam(value = "spicy", required = false) Boolean spicy) {
        return this.service.getPaged(spicy, pageable);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable String id) {
        return this.service.findByName(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody @Valid IngredientRequest request) {
        return this.service.save(request);
    }

    @PatchMapping("/{id}")
    public Ingredient updateIngredient(@RequestBody @Valid IngredientRequest request, @PathVariable String id) {
        return this.service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable String id) {
        this.service.delete(id);
    }

    @GetMapping("{id}/pizzas")
    public List<Pizza> getPizzas(@PathVariable String id) {
        return this.service.getPizzas(id);
    }
}
