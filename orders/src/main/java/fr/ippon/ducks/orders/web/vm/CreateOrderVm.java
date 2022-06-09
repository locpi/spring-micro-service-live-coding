package fr.ippon.ducks.orders.web.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateOrderVm {

    private final String reference;
    private final String clientName;
    private final int qtte;

}
