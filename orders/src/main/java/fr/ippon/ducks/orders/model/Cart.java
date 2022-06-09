package fr.ippon.ducks.orders.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "is_open")
    private boolean open;

    @ManyToMany
    private List<DuckOrder> products;

    @Column(name = "identifiant")
    @Type(type = "uuid-char")
    private UUID identifiant;

}
