package fr.ippon.ducks.products.demo.service.impl;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import fr.ippon.ducks.products.demo.model.Duck;
import fr.ippon.ducks.products.demo.model.model.DuckColor;
import fr.ippon.ducks.products.demo.model.model.DuckSize;
import fr.ippon.ducks.products.demo.repository.DuckRepository;
import fr.ippon.ducks.products.demo.service.DuckService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class DuckServiceImpl implements DuckService {


    private final DuckRepository duckRepository;

    private final CreateProductProduder reateProductProducer;

    @Override
    public List<Duck> getDucks() {
        return duckRepository.findAll();
    }

    @Override
    @Transactional
    public Duck createNewDuck(DuckColor color, DuckSize size, String slug, int stock, double price) {
        if (stock < 1) {
            throw new ResponseStatusException(BAD_REQUEST, "duck stock can't be less than zero");
        }

        if (!StringUtils.hasText(slug)) {
            throw new ResponseStatusException(BAD_REQUEST, "duck stock can't be empty");
        }

        Optional<Duck> duckOptional = duckRepository.findBySizeAndColor(size, color);
        Duck duckToSave;
        if (duckOptional.isPresent()) {
            duckToSave = duckOptional.get();
            duckToSave.setStock(duckToSave.getStock() + stock);
        } else {
            duckToSave = new Duck();
            StringBuilder reference = new StringBuilder();
            reference.append(color.name());
            reference.append("-");
            reference.append(size.name());
            duckToSave.setSize(size);
            duckToSave.setColor(color);
            duckToSave.setPrice(price);
            duckToSave.setReference(reference.toString());
            duckToSave.setSlug(slug);
            duckToSave.setStock(stock);
        }
        duckRepository.save(duckToSave);
        createProductProducer.send(duckToSave);
        return duckToSave;
    }


}
