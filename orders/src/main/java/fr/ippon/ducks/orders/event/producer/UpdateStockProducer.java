package fr.ippon.ducks.orders.event.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ippon.ducks.orders.event.producer.model.UpdateStockMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UpdateStockProducer {

    private final JmsTemplate jmsTemplate;

    private final ObjectMapper mapper = new ObjectMapper();


    public void send(UpdateStockMessage duckOrder) {
        try {
            jmsTemplate.convertAndSend("update_stock", mapper.writeValueAsString(duckOrder));
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Impossible to get the stock");
        }
    }

}
