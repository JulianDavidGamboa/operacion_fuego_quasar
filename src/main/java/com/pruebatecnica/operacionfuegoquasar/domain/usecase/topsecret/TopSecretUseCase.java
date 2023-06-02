package com.pruebatecnica.operacionfuegoquasar.domain.usecase.topsecret;

import com.pruebatecnica.operacionfuegoquasar.domain.model.Satellite;
import com.pruebatecnica.operacionfuegoquasar.domain.model.TopSecretRequest;
import com.pruebatecnica.operacionfuegoquasar.domain.model.TopSecretResponse;
import com.pruebatecnica.operacionfuegoquasar.utils.Utils;
import lombok.AllArgsConstructor;

import java.awt.Point;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;

@Component
@AllArgsConstructor
public class TopSecretUseCase {

    private static Logger log = LoggerFactory.getLogger(TopSecretUseCase.class);

    @Autowired
    private Utils utils;

    public TopSecretResponse topSecret(TopSecretRequest request) {
        String message = utils.getMessage(request);
        Point location = utils.getLocation(request);

        return new TopSecretResponse(location, message);
    }


}
