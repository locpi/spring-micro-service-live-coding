package fr.ippon.ducks.products.demo.model.model;

import java.util.Arrays;
import java.util.Optional;

public enum DuckColor {
    GREEN, BLUE, YELLOW;


    public static Optional<DuckColor> getColor(String color) {
        return Arrays.stream(DuckColor.values()).filter(c -> c.name().equals(color)).findFirst();
    }
}
