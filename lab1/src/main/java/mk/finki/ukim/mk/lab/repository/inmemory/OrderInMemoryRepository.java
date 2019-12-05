package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderInMemoryRepository {
    private static final List<Order> orders = new ArrayList<>();

    public OrderInMemoryRepository() { }

    public Order saveOrder(Order order) {
        this.findById(order.getOrderId()).ifPresent(orders::remove);
        orders.add(order);
        return order;
    }

    public Optional<Order> findById(Long orderId) {
        return orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst();
    }
}
