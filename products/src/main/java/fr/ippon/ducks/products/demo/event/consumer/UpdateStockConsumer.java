package fr.ippon.ducks.products.demo.event.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ippon.ducks.products.demo.event.consumer.model.UpdateStockMessage;
import fr.ippon.ducks.products.demo.service.DuckService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateStockConsumer {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(UpdateStockConsumer.class);

    private final DuckService duckService;

    private final ObjectMapper mapper = new ObjectMapper();


    @JmsListener(destination = "update_stock")
    public void receive(String message) throws JsonProcessingException {
        LOGGER.info("received message='{}'", message);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        UpdateStockMessage updateStockMessage = mapper.readValue(message, UpdateStockMessage.class);
        duckService.updateStock(updateStockMessage.getReference(), updateStockMessage.getQtteInOrder());
    }

}
