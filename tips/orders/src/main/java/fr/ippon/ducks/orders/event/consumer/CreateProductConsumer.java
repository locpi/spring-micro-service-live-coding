package fr.ippon.ducks.orders.event.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ippon.ducks.orders.event.consumer.model.NewDuckMessage;
import fr.ippon.ducks.orders.service.DuckOrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class CreateProductConsumer {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(CreateProductConsumer.class);
    private final ObjectMapper mapper = new ObjectMapper();

    private final DuckOrderService duckOrderService;

    @JmsListener(destination = "create_product")
    public void send(String message) {
        try {
            NewDuckMessage newDuckMessage = mapper.readValue(message, NewDuckMessage.class);
            LOGGER.info(message);
            duckOrderService.registerNewDuckOrder(newDuckMessage.getReference(), newDuckMessage.getStock(), newDuckMessage.getPrice());
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Impossible to save the product");
        }
    }

}
