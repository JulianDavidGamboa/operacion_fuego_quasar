package com.pruebatecnica.operacionfuegoquasar.infrastructure.drivenadapter;

import java.awt.*;

public interface ISatelliteRepository {
    Point getKenobiPosition();
    Point getSkywalkerPosition();
    Point getSatoPosition();
}
