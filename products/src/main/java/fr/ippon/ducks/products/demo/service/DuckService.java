package fr.ippon.ducks.products.demo.service;


import fr.ippon.ducks.products.demo.model.Duck;
import fr.ippon.ducks.products.demo.model.model.DuckColor;
import fr.ippon.ducks.products.demo.model.model.DuckSize;
import java.util.List;


public interface DuckService {

    List<Duck> getDucks();

    Duck createNewDuck(DuckColor color, DuckSize size, String slug, int stock, double price);

    void updateStock(String reference,int quantityToLess);
}
