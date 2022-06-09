package fr.ippon.ducks.orders.web.vm;

import fr.ippon.ducks.orders.model.DuckOrder;
import fr.ippon.ducks.orders.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OrderVm {

    private long id;
    private String clientName;
    private int quantite;
    private String duckReference;
    private double duckPrice;

    public static OrderVm mapFrom(Order entity) {
        return new OrderVm(entity.getId(), entity.getClientName(), entity.getQuantity(), entity.getReference().getReference(),
            entity.getReference().getPrice());
    }

    public double getPrice() {
        return quantite * duckPrice;
    }
}
