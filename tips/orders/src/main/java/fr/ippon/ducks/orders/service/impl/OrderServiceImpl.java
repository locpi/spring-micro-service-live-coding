package fr.ippon.ducks.orders.service.impl;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import fr.ippon.ducks.orders.event.producer.UpdateStockProducer;
import fr.ippon.ducks.orders.event.producer.model.UpdateStockMessage;
import fr.ippon.ducks.orders.model.DuckOrder;
import fr.ippon.ducks.orders.model.Order;
import fr.ippon.ducks.orders.repository.DuckOrderRepository;
import fr.ippon.ducks.orders.repository.OrderRepository;
import fr.ippon.ducks.orders.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DuckOrderRepository duckOrderRepository;
    private final UpdateStockProducer updateStockProducer;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order createNewOrder(String reference, String clientName, int qtte) {
        DuckOrder duck = duckOrderRepository.findByReference(reference)
            .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "This duck is unknow"));
        if (duck.getStock() < qtte) {
            throw new ResponseStatusException(BAD_REQUEST, "the stock are less than the order quantity");
        }
        Order order = new Order();
        order.setReference(duck);
        order.setClientName(clientName);
        order.setQuantity(qtte);
        duck.setStock(duck.getStock() - qtte);
        duckOrderRepository.save(duck);
        updateStockProducer.send(new UpdateStockMessage(duck.getReference(), duck.getStock()));
        return orderRepository.save(order);
    }

}
