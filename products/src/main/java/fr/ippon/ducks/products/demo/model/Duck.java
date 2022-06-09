package fr.ippon.ducks.products.demo.model;

import fr.ippon.ducks.products.demo.model.model.DuckColor;
import fr.ippon.ducks.products.demo.model.model.DuckSize;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ducks_products")
@Getter
@Setter
public class Duck {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private DuckSize size;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private DuckColor color;

    @Column(name = "slug")
    private String slug;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price")
    private double price;

    @Column(name = "reference",unique = true)
    private String reference;

    public Duck() {

    }


}
