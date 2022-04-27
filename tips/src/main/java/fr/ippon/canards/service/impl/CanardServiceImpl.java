package fr.ippon.canards.service.impl;

import fr.ippon.canards.model.Canard;
import fr.ippon.canards.repository.CanardRepository;
import fr.ippon.canards.service.CanardService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CanardServiceImpl implements CanardService {

    private final CanardRepository canardRepository;

    public CanardServiceImpl(CanardRepository canardRepository) {
        this.canardRepository = canardRepository;
    }

    @Override
    public List<Canard> recupererCanard() {
        return canardRepository.findAll();
    }



}
