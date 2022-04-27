package fr.ippon.canards.service;

import fr.ippon.canards.model.Canard;
import java.util.List;
import org.springframework.stereotype.Service;


public interface CanardService {

    List<Canard> recupererCanard();

}
