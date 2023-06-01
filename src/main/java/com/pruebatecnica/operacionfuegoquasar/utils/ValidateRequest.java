package com.pruebatecnica.operacionfuegoquasar.utils;

import com.pruebatecnica.operacionfuegoquasar.domain.model.Satellite;
import com.pruebatecnica.operacionfuegoquasar.domain.model.TopSecretRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidateRequest {

    public boolean isRequestValid(TopSecretRequest request) {
        List<Satellite> satellites = request.getSatellites();

        // Verificar si todos los satélites tienen nombre, distancia y mensaje no vacíos
        for (Satellite satellite : satellites) {
            if (satellite.getName() == null || satellite.getName().isEmpty()
                    || satellite.getDistance() == 0
                    || satellite.getMessage() == null || satellite.getMessage().length == 0) {
                return false;
            }
        }

        return true;
    }

}
