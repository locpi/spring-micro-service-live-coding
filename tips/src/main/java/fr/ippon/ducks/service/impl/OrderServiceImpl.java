package fr.ippon.ducks.service.impl;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import fr.ippon.ducks.model.Duck;
import fr.ippon.ducks.model.Order;
import fr.ippon.ducks.repository.DuckRepository;
import fr.ippon.ducks.repository.OrderRepository;
import fr.ippon.ducks.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DuckRepository duckRepository;


    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order createNewOrder(String reference, String clientName, int qtte) {
        Duck duck = duckRepository.findByReference(reference)
            .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "This duck is unknow"));
        if (duck.getStock() < qtte) {
            throw new ResponseStatusException(BAD_REQUEST, "the stock are less than the order quantity");
        }
        Order order = new Order();
        order.setDuck(duck);
        order.setClientName(clientName);
        order.setQuantite(qtte);
        duck.setStock(duck.getStock() - qtte);
        duckRepository.save(duck);
        return orderRepository.save(order);
    }
}
