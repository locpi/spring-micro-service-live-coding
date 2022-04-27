package fr.ippon.canards.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ducks_products")
public class Canard {

    @Id
    @Column(name = "id")
    private int id;


    @Column(name = "size")
    private String taille;

    @Column(name = "color")
    private String couleur;

    @Column(name = "stock")
    private int stock;

    public Canard(String taille, String couleur, int stock) {
        this.taille = taille;
        this.couleur = couleur;
        this.stock = stock;
    }

    public Canard() {

    }

    public String getCouleur() {
        return couleur;
    }

    public String getTaille() {
        return taille;
    }

    public int getStock() {
        return stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
