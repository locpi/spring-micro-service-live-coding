package fr.ippon.ducks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity()
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "client_name")
    private String clientName;


    @Column(name = "qtt")
    private int quantite;

    @OneToOne
    @JoinColumn(name = "duck")
    private Duck duck;

}
