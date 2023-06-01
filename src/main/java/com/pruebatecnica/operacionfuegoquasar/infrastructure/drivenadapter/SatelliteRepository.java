package com.pruebatecnica.operacionfuegoquasar.infrastructure.drivenadapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.awt.*;

@Component
public class SatelliteRepository implements ISatelliteRepository {

    @Value("${satellite.position.kenobi.x}")
    private int kenobiPositionX;

    @Value("${satellite.position.kenobi.y}")
    private int kenobiPositionY;

    @Value("${satellite.position.skywalker.x}")
    private int skywalkerPositionX;

    @Value("${satellite.position.skywalker.y}")
    private int skywalkerPositionY;

    @Value("${satellite.position.sato.x}")
    private int satoPositionX;

    @Value("${satellite.position.sato.y}")
    private int satoPositionY;

    public Point getKenobiPosition() {
        return new Point(kenobiPositionX, kenobiPositionY);
    }

    public Point getSkywalkerPosition() {
        return new Point(skywalkerPositionX, skywalkerPositionY);
    }

    public Point getSatoPosition() {
        return new Point(satoPositionX, satoPositionY);
    }


}
