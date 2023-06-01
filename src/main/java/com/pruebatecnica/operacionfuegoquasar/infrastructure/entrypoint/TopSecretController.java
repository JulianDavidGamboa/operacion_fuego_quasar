package com.pruebatecnica.operacionfuegoquasar.infrastructure.entrypoint;

import com.pruebatecnica.operacionfuegoquasar.domain.model.TopSecretRequest;
import com.pruebatecnica.operacionfuegoquasar.domain.model.TopSecretResponse;
import com.pruebatecnica.operacionfuegoquasar.domain.usecase.TopSecretUseCase;
import com.pruebatecnica.operacionfuegoquasar.utils.ValidateRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/topsecret")
@AllArgsConstructor
public class TopSecretController {

    private final TopSecretUseCase topSecretUseCase;

    private final ValidateRequest validateRequest;

    private static Logger log = LoggerFactory.getLogger(TopSecretController.class);

    @PostMapping()
    public ResponseEntity getUbicationAndMessage(@RequestBody TopSecretRequest request) {

        // Point location = new Point(100, 200);


        if(!validateRequest.isRequestValid(request)) {
            TopSecretResponse response = new TopSecretResponse();

            response.setLocation(new Point(0, 0));
            response.setMessage("Parametros incorrectos. Por favor validar.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        String message = topSecretUseCase.getMessage(request);
        Point location = topSecretUseCase.getLocation(request);

        log.info("Message: ", message);
        log.info("Location: ", location);

        TopSecretResponse response = new TopSecretResponse();

        response.setLocation(location);
        response.setMessage(message);

        return ResponseEntity.ok(response);


    }
}
