package fr.ippon.ducks.orders.event.producer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateStockMessage {


    private final String reference;

    private final int qtteInOrder;

}
