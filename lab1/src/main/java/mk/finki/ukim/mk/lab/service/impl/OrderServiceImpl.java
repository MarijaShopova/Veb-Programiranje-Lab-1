package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.inmemory.OrderInMemoryRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderInMemoryRepository repository;

    public OrderServiceImpl(OrderInMemoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order placeOrder(String pizzaType, String clientName, String address) {
        Random r = new Random();
//        return this.repository.saveOrder(new Order(pizzaType, clientName, address, r.nextLong()));
        return null;
    }
}
