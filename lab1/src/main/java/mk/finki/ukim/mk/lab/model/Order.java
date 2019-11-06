package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Order {

    private String pizzaType;

    private String clientName;

    private String clientAddress;

    private Long orderId;
}
