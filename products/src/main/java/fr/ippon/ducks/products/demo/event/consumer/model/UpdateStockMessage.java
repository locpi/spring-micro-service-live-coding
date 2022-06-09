package fr.ippon.ducks.products.demo.event.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateStockMessage {


    private  String reference;

    private  int qtteInOrder;

}
