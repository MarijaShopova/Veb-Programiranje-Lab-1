package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {
    private PizzaRepository repository;

    public PizzaServiceImpl(PizzaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Pizza> listPizzas() {
        return this.repository.getAllPizzas();
    }
}
