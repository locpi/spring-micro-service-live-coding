package fr.ippon.ducks.orders.service.impl;

import fr.ippon.ducks.orders.model.Cart;
import fr.ippon.ducks.orders.model.DuckOrder;
import fr.ippon.ducks.orders.repository.CartRepository;
import fr.ippon.ducks.orders.repository.DuckOrderRepository;
import fr.ippon.ducks.orders.service.CartService;
import fr.ippon.ducks.orders.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final DuckOrderRepository duckOrderRepository;
    private final OrderService orderService;

    @Override
    public Cart addToCart(UUID idCart, String refProduct) {
        Cart cart = cartRepository.findByIdentifiant(idCart)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "unknow cart"));

        if (!cart.isOpen()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cart is close");
        }

        DuckOrder duckOrder = duckOrderRepository.findByReference(refProduct)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "product is unknow"));

        if (Objects.isNull(cart.getProducts())) {
            cart.setProducts(new ArrayList<>());
        }

        cart.getProducts().add(duckOrder);

        return cart;
    }

    @Override
    public Cart getClientCart(String clientName) {
        Optional<Cart> cart = cartRepository.findByClientNameAndOpen(clientName, true);
        if (cart.isPresent()) {
            return cart.get();
        }
        Cart newCart = new Cart();
        newCart.setClientName(clientName);
        newCart.setIdentifiant(UUID.randomUUID());
        newCart.setOpen(true);
        newCart.setProducts(new ArrayList<>());
        return cartRepository.save(newCart);
    }

    @Override
    public Cart getClientCart(UUID idCart) {
        return cartRepository.findByIdentifiant(idCart)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "unknow cart"));
    }

    @Override
    public Cart closeCartAndLaunchOrder(UUID idCart) {
        Cart cart = cartRepository.findByIdentifiant(idCart)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "unknow cart"));

        List<String> collect = cart.getProducts().stream().map(DuckOrder::getReference).collect(Collectors.toList());

        orderService.createNewOrder(collect, cart.getClientName());
        return cart;
    }
}
