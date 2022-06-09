package fr.ippon.ducks.orders.service;

import fr.ippon.ducks.orders.model.Cart;
import fr.ippon.ducks.orders.model.Order;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface CartService {

    Cart addToCart(UUID idCart, String refProduct);

    Cart getClientCart(String clientName);

    Cart getClientCart(UUID idCart);

    Cart closeCartAndLaunchOrder(UUID idCart);
}
