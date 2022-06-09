package fr.ippon.ducks.orders.repository;

import fr.ippon.ducks.orders.model.DuckOrder;
import fr.ippon.ducks.orders.model.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


}
