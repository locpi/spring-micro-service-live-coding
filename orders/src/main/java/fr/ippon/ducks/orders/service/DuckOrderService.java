package fr.ippon.ducks.orders.service;

public interface DuckOrderService {

    void registerNewDuckOrder(String reference, int stock,double price);

}
