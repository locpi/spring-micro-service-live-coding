package fr.ippon.ducks.orders.repository;

import fr.ippon.ducks.orders.model.Cart;
import fr.ippon.ducks.orders.model.Order;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByClientNameAndOpen(String client, boolean open);

    Optional<Cart> findByIdentifiant(UUID id);

}
