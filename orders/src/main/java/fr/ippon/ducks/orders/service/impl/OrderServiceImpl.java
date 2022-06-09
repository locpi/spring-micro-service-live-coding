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
import java.util.stream.Collectors;
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
    public Order createNewOrder(List<String> reference, String clientName) {

        List<DuckOrder> this_duck_is_unknow = reference.stream().map(ref -> duckOrderRepository.findByReference(ref)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "This duck is unknow " + ref)))
            .collect(Collectors.toList());

        this_duck_is_unknow.forEach(duck -> {
            if (duck.getStock() < 1) {
                throw new ResponseStatusException(BAD_REQUEST, "the stock are less than the order quantity duck " + duck.getReference());
            }
        });

        this_duck_is_unknow.forEach(duck -> {
            duck.setStock(duck.getStock() - 1);
            duckOrderRepository.save(duck);
            updateStockProducer.send(new UpdateStockMessage(duck.getReference(), 1));
        });

        Order order = new Order();
        order.setReferences(this_duck_is_unknow);
        order.setClientName(clientName);
        return orderRepository.save(order);
    }

}
