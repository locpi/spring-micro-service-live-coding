package fr.ippon.canards.model;

import java.util.Arrays;
import java.util.Optional;

public enum DuckSize {
    SMALL,LARGE
    ;


    public static Optional<DuckSize> getSize(String size) {
        return Arrays.stream(DuckSize.values()).filter(c -> c.name().equals(size)).findFirst();
    }
}
