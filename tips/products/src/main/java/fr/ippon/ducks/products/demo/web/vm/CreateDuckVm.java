package fr.ippon.ducks.products.demo.web.vm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDuckVm {

    private String size;
    private String color;
    private String slug;
    private int stock;
    private double price;

}
