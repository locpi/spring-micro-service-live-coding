package fr.ippon.ducks.web.vm;

import fr.ippon.ducks.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OrderVm {

    private int id;
    private String clientName;
    private int quantite;
    private String duckReference;
    private double duckPrice;

    public static OrderVm mapFrom(Order entity) {
        return new OrderVm(entity.getId(), entity.getClientName(), entity.getQuantite(), entity.getDuck().getReference(),
            entity.getDuck().getPrice());
    }

    public double getPrice() {
        return quantite * duckPrice;
    }
}
