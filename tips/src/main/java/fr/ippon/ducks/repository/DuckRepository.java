package fr.ippon.ducks.repository;

import fr.ippon.ducks.model.Duck;
import fr.ippon.ducks.model.model.DuckColor;
import fr.ippon.ducks.model.model.DuckSize;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface DuckRepository extends JpaRepository<Duck, Long> {

    Optional<Duck> findBySizeAndColor(DuckSize size, DuckColor color);
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    Optional<Duck> findByReference(String reference);

}
