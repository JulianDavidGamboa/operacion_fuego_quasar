package com.pruebatecnica.operacionfuegoquasar.domain.usecase.topsecretsplit;

import com.pruebatecnica.operacionfuegoquasar.domain.model.Satellite;
import com.pruebatecnica.operacionfuegoquasar.domain.model.SatelliteUpdateRequest;
import com.pruebatecnica.operacionfuegoquasar.domain.model.TopSecretRequest;
import com.pruebatecnica.operacionfuegoquasar.domain.model.TopSecretResponse;
import com.pruebatecnica.operacionfuegoquasar.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TopSecretSplitUseCase {
    private final Map<String, SatelliteUpdateRequest> satelliteDataMap = new HashMap<>();

    @Autowired
    private Utils utils;

    public boolean saveSplitData(String satelliteName, SatelliteUpdateRequest satelliteSplitRequest) {
        if (satelliteName != null && satelliteSplitRequest.getMessage() != null && satelliteSplitRequest.getDistance() != 0) {
            satelliteDataMap.put(satelliteName, satelliteSplitRequest);
            return true;
        }
        return false;
    }

    public TopSecretResponse getTopSecretSplitData() {
        if (satelliteDataMap.size() < 3) {
            return null;
        }
        List<Satellite> satellites = satelliteDataMap.entrySet().stream()
                .map(entry -> {
                    String name = entry.getKey();
                    SatelliteUpdateRequest satelliteData = entry.getValue();
                    return new Satellite(name, satelliteData.getDistance(), satelliteData.getMessage());
                })
                .collect(Collectors.toList());

        TopSecretRequest topSecretRequest = new TopSecretRequest(satellites);

        // Calcular la posición y el mensaje
        Point location = utils.getLocation(topSecretRequest);
        String message = utils.getMessage(topSecretRequest);

        if (location != null && !message.isEmpty()) {
            return new TopSecretResponse(location, message);
        } else {
            return null;
        }
    }
}
