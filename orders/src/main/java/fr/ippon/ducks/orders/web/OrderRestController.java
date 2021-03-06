package fr.ippon.ducks.orders.web;

import static fr.ippon.ducks.orders.web.vm.OrderVm.mapFrom;
import static java.util.stream.Collectors.toList;

import fr.ippon.ducks.orders.service.OrderService;
import fr.ippon.ducks.orders.web.vm.CreateOrderVm;
import fr.ippon.ducks.orders.web.vm.OrderVm;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/orders")
@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderVm> getDucks() {
        return orderService.getOrders().stream()
            .map(OrderVm::mapFrom).
            collect(toList());
    }

    @PostMapping
    public OrderVm createDuck(@RequestBody CreateOrderVm createDuckVm) {
        return mapFrom(
            orderService.createNewOrder(createDuckVm.getReference(), createDuckVm.getClientName(), createDuckVm.getQtte()));
    }

}
