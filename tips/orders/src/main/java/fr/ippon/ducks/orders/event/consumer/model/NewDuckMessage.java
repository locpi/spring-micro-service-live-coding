package fr.ippon.ducks.orders.event.consumer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NewDuckMessage {

    private String reference;

    private int stock;

    private double price;

}
