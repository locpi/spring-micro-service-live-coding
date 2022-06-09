package fr.ippon.ducks.orders.repository;

import fr.ippon.ducks.orders.model.DuckOrder;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface DuckOrderRepository extends JpaRepository<DuckOrder, Long> {

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    Optional<DuckOrder> findByReference(String reference);

}
