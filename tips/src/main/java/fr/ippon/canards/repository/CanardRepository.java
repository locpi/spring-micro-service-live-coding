package fr.ippon.canards.repository;

import fr.ippon.canards.model.Canard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CanardRepository extends JpaRepository<Canard, Long> {

}
