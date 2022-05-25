package fr.ippon.canards;

import fr.ippon.canards.model.Duck;
import fr.ippon.canards.repository.DuckRepository;
import fr.ippon.canards.repository.OrderRepository;
import fr.ippon.canards.web.vm.CreateDuckVm;
import fr.ippon.canards.web.vm.CreateOrderVm;
import fr.ippon.canards.web.vm.OrderVm;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class DuckApplicationTest {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private DuckRepository duckRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testpetitecharge() {
        orderRepository.deleteAll();
        duckRepository.deleteAll();

        createDuck("SMALL", "BLUE", "SMALL DUCK BLUE", 10, 1);
        createDuck("LARGE", "BLUE", "LARGE DUCK BLUE", 9, 2);
        createDuck("LARGE", "GREEN", "LARGE DUCK GREEN", 10, 3);

        List<Duck> ducks = getDucks();

        newOrder("GREEN-LARGE", "SuperDuck One", 5);
        newOrder("GREEN-LARGE", "SuperDuck One", 3);

        Assertions.assertThatThrownBy(() -> newOrder("GREEN-LARGE", "SuperDuck One", 3));

    }


    @Test
    void testgrosserage() throws InterruptedException {
        orderRepository.deleteAll();
        duckRepository.deleteAll();

        createDuck("SMALL", "BLUE", "SMALL DUCK BLUE", 10, 1);
        createDuck("LARGE", "BLUE", "LARGE DUCK BLUE", 9, 2);
        createDuck("LARGE", "GREEN", "LARGE DUCK GREEN", 1000000000, 3);

        while (true) {
            Thread.sleep(2000);
            for(int i=0;i<50;i++){
                newOrder("GREEN-LARGE", "SuperDuck One", 5);
                newOrder("GREEN-LARGE", "SuperDuck One", 3);

            }
        }
    }

    private List<Duck> getDucks() {
        return Arrays.asList(restTemplate.getForEntity("http://localhost:8080/api/ducks", Duck[].class).getBody());
    }

    private void newOrder(String reference, String clientName, int qtte) {
        CreateOrderVm createOrderVm = new CreateOrderVm(reference, clientName, qtte);
        restTemplate.postForEntity("http://localhost:8080/api/orders", createOrderVm, OrderVm.class);
    }

    private void createDuck(String size, String color, String slug, int stock, double price) {
        CreateDuckVm createDuckVm = new CreateDuckVm();
        createDuckVm.setSize(size);
        createDuckVm.setColor(color);
        createDuckVm.setSlug(slug);
        createDuckVm.setStock(stock);
        createDuckVm.setPrice(price);
        restTemplate.postForEntity("http://localhost:8080/api/ducks", createDuckVm, Duck.class);

    }

}
