package fr.ippon.canards.service;

import fr.ippon.canards.model.Order;
import java.util.List;


public interface OrderService {

    List<Order> getOrders();

    Order createNewOrder(String reference, String clientName, int qtte);
}
