package fr.ippon.ducks.products.demo.event.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ippon.ducks.products.demo.model.Duck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CreateProductProducer {

    private final JmsTemplate jmsTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    public void send(Duck duck) {
        try {
            jmsTemplate.convertAndSend("create_product", mapper.writeValueAsString(duck));
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Impossible to save the product");
        }
    }

}
