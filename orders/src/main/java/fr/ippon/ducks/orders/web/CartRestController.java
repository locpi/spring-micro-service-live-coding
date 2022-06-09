package fr.ippon.ducks.orders.web;

import fr.ippon.ducks.orders.model.Cart;
import fr.ippon.ducks.orders.model.DuckOrder;
import fr.ippon.ducks.orders.service.CartService;
import fr.ippon.ducks.orders.web.vm.AddProductCartVm;
import fr.ippon.ducks.orders.web.vm.CartVm;
import fr.ippon.ducks.orders.web.vm.CreateCartVm;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartRestController {

    private final CartService cartService;

    @PostMapping
    public CartVm createCart(@RequestBody CreateCartVm cartVm) {
        Cart clientCart = cartService.getClientCart(cartVm.getClientName());
        return CartVm.builder()
            .clientName(clientCart.getClientName())
            .references(clientCart.getProducts().stream().map(DuckOrder::getReference).collect(Collectors.toList()))
            .id(clientCart.getIdentifiant().toString())
            .build();
    }

    @PutMapping("/{idCart}")
    public CartVm addProduct(@PathVariable UUID idCart, @RequestBody AddProductCartVm addProductCartVm) {
        Cart cart = cartService.addToCart(idCart, addProductCartVm.getReference());
        return CartVm.builder()
            .clientName(cart.getClientName())
            .references(cart.getProducts().stream().map(DuckOrder::getReference).collect(Collectors.toList()))
            .id(cart.getIdentifiant().toString())
            .build();
    }

    @GetMapping("/{idCart}")
    public CartVm getCart(@PathVariable UUID idCart) {
        Cart cart = cartService.getClientCart(idCart);
        return CartVm.builder()
            .clientName(cart.getClientName())
            .references(cart.getProducts().stream().map(DuckOrder::getReference).collect(Collectors.toList()))
            .id(cart.getIdentifiant().toString())
            .build();
    }


    @PostMapping("/{idCart}/close")
    public CartVm closeCart(@PathVariable UUID idCart) {
        Cart cart = cartService.closeCartAndLaunchOrder(idCart);
        return CartVm.builder()
            .clientName(cart.getClientName())
            .references(cart.getProducts().stream().map(DuckOrder::getReference).collect(Collectors.toList()))
            .id(cart.getIdentifiant().toString())
            .build();
    }


}
