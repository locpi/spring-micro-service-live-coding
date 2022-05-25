package fr.ippon.canards.service;

import fr.ippon.canards.model.Duck;
import fr.ippon.canards.model.DuckColor;
import fr.ippon.canards.model.DuckSize;
import java.util.List;


public interface DuckService {

    List<Duck> getDucks();

    Duck createNewDuck(DuckColor color, DuckSize size, String slug, int stock, double price);
}
