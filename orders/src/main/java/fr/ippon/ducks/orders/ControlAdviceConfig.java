package fr.ippon.ducks.orders;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
@Configuration
public class ControlAdviceConfig {


    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity resourceNotFoundException(ResponseStatusException ex, WebRequest request) {
        ex.printStackTrace();
        return ResponseEntity.status(ex.getStatus().value()).body(ex.getMessage());
    }

}
