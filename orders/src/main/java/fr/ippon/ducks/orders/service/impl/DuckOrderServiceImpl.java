package fr.ippon.ducks.orders.service.impl;

import fr.ippon.ducks.orders.model.DuckOrder;
import fr.ippon.ducks.orders.repository.DuckOrderRepository;
import fr.ippon.ducks.orders.service.DuckOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DuckOrderServiceImpl implements DuckOrderService {

    private final DuckOrderRepository duckOrderRepository;

    @Override
    public void registerNewDuckOrder(String reference, int stock, double price) {
        duckOrderRepository.findByReference(reference).ifPresentOrElse(duck -> {
            duck.setStock(stock);
            duck.setPrice(price);
            duckOrderRepository.save(duck);
        }, () -> {
            DuckOrder duckOrder = new DuckOrder();
            duckOrder.setReference(reference);
            duckOrder.setPrice(price);
            duckOrder.setStock(stock);
            duckOrderRepository.save(duckOrder);
        });

    }
}
