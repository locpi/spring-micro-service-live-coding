package fr.ippon.canards.web;

import fr.ippon.canards.model.Canard;
import fr.ippon.canards.service.CanardService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/canards")
@RestController
public class CanardRestController {

    private final CanardService canardService;

    public CanardRestController(CanardService canardService) {
        this.canardService = canardService;
    }

    @GetMapping
    public List<Canard> getCanards() {
        return canardService.recupererCanard();
    }

}
