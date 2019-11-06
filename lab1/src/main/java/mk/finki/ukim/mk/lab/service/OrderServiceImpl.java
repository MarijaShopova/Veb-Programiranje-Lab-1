package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order placeOrder(String pizzaType, String clientName, String address) {
        Random r = new Random();
        return this.repository.saveOrder(new Order(pizzaType, clientName, address, r.nextLong()));
    }
}
