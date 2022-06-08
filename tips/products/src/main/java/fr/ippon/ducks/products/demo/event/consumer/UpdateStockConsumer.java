package fr.ippon.ducks.products.demo.event.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class UpdateStockConsumer {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(UpdateStockConsumer.class);

    @JmsListener(destination = "update_stock")
    public void receive(String message) {
        LOGGER.info("received message='{}'", message);
    }

}
