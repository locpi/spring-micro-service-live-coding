package fr.ippon.ducks.service;

import fr.ippon.ducks.model.Order;
import java.util.List;


public interface OrderService {

    List<Order> getOrders();

    Order createNewOrder(String reference, String clientName, int qtte);
}
