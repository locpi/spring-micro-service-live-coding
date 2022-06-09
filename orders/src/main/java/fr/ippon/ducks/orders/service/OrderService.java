package fr.ippon.ducks.orders.service;

import fr.ippon.ducks.orders.model.Order;
import java.util.Collection;

public interface OrderService {

    Collection<Order> getOrders();

    Order createNewOrder(String reference, String clientName, int qtte);
}
