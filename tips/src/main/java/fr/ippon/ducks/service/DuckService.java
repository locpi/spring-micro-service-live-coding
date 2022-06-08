package fr.ippon.ducks.service;

import fr.ippon.ducks.model.Duck;
import fr.ippon.ducks.model.model.DuckColor;
import fr.ippon.ducks.model.model.DuckSize;
import java.util.List;


public interface DuckService {

    List<Duck> getDucks();

    Duck createNewDuck(DuckColor color, DuckSize size, String slug, int stock, double price);
}
