package com.pruebatecnica.operacionfuegoquasar.infrastructure.entrypoint;

import com.pruebatecnica.operacionfuegoquasar.domain.model.MessageResponse;
import com.pruebatecnica.operacionfuegoquasar.domain.model.Satellite;
import com.pruebatecnica.operacionfuegoquasar.domain.model.SatelliteUpdateRequest;
import com.pruebatecnica.operacionfuegoquasar.domain.model.TopSecretResponse;
import com.pruebatecnica.operacionfuegoquasar.domain.usecase.topsecretsplit.TopSecretSplitUseCase;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class TopSecretSplitController {

    private static Logger log = LoggerFactory.getLogger(TopSecretSplitController.class);

    private final TopSecretSplitUseCase topSecretSplitUseCase;

    private final Map<String, Satellite> satelliteMap = new HashMap<>();

    @PostMapping("/topsecret_split/{satellite_name}")
    public ResponseEntity<Object> updateSatellite(@PathVariable("satellite_name") String satelliteName, @RequestBody SatelliteUpdateRequest request) {
        log.info("Registrando informacion de " + satelliteName);
        boolean success = topSecretSplitUseCase.saveSplitData(satelliteName, request);
        if (success) {
            MessageResponse errorResponse = new MessageResponse(HttpStatus.OK, "Registro ingresado correctamente.");
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        } else {
            MessageResponse errorResponse = new MessageResponse(HttpStatus.NOT_FOUND, "No se puede guardar la información del satélite.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/topsecret_split")
    public ResponseEntity<Object> getTopSecretData() {
        log.info("Entrando al controlador GET");
        TopSecretResponse topSecretResponse = topSecretSplitUseCase.getTopSecretSplitData();
        if (topSecretResponse != null) {
            return ResponseEntity.ok(topSecretResponse);
        } else {
            MessageResponse errorResponse = new MessageResponse(HttpStatus.NOT_FOUND, "No hay suficiente información para determinar la posición y el mensaje.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
