package fr.ippon.canards.web;

import fr.ippon.canards.model.Duck;
import fr.ippon.canards.model.DuckColor;
import fr.ippon.canards.model.DuckSize;
import fr.ippon.canards.service.DuckService;
import fr.ippon.canards.web.vm.CreateDuckVm;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/api/ducks")
@RestController
@RequiredArgsConstructor
public class DuckRestController {

    private final DuckService duckService;

    @GetMapping
    public List<Duck> getDucks() {
        return duckService.getDucks();
    }

    @PostMapping
    public Duck createDuck(@RequestBody CreateDuckVm createDuckVm) {

        DuckColor duckColor = DuckColor.getColor(createDuckVm.getColor())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Color is unknow : " + createDuckVm.getColor()));
        DuckSize duckSize = DuckSize.getSize(createDuckVm.getSize())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size is unknow : " + createDuckVm.getColor()));

        return duckService.createNewDuck(duckColor, duckSize, createDuckVm.getSlug(), createDuckVm.getStock(),createDuckVm.getPrice());
    }

}
