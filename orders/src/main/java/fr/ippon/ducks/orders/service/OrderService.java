package fr.ippon.ducks.orders.service;

import fr.ippon.ducks.orders.model.Order;
import java.util.Collection;
import java.util.List;

public interface OrderService {

    Collection<Order> getOrders();

    Order createNewOrder(List<String> references, String clientName);
}
