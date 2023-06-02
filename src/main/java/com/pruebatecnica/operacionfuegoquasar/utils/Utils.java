package com.pruebatecnica.operacionfuegoquasar.utils;

import com.pruebatecnica.operacionfuegoquasar.domain.model.Satellite;
import com.pruebatecnica.operacionfuegoquasar.domain.model.TopSecretRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.geom.Point2D;

@Component
public class Utils {

    @Value("${satellite.position.kenobi.x}")
    private double kenobiPositionX;

    @Value("${satellite.position.kenobi.y}")
    private double kenobiPositionY;

    @Value("${satellite.position.skywalker.x}")
    private double skywalkerPositionX;

    @Value("${satellite.position.skywalker.y}")
    private double skywalkerPositionY;

    @Value("${satellite.position.sato.x}")
    private double satoPositionX;

    @Value("${satellite.position.sato.y}")
    private double satoPositionY;

    public Point getLocation(TopSecretRequest satellites) {

        double x1 = kenobiPositionX;
        double y1 = kenobiPositionY;
        double x2 = skywalkerPositionX;
        double y2 = skywalkerPositionY;
        double x3 = satoPositionX;
        double y3 = satoPositionY;

        double[] satellitesDistances = satellites.getSatellites().stream().mapToDouble(Satellite::getDistance).toArray();

        double r1 = satellitesDistances[0];
        double r2 = satellitesDistances[1];
        double r3 = satellitesDistances[2];

        double A = 2 * (x2 - x1);
        double B = 2 * (y2 - y1);
        double C = Math.pow(r1, 2) - Math.pow(r2, 2) - Math.pow(x1, 2) + Math.pow(x2, 2) - Math.pow(y1, 2) + Math.pow(y2, 2);
        double D = 2 * (x3 - x2);
        double E = 2 * (y3 - y2);
        double F = Math.pow(r2, 2) - Math.pow(r3, 2) - Math.pow(x2, 2) + Math.pow(x3, 2) - Math.pow(y2, 2) + Math.pow(y3, 2);

        double x = (C * E - F * B) / (E * A - B * D);
        double y = (C * D - A * F) / (B * D - A * E);

        Point2D.Double point = new Point2D.Double(x, y);

        Point pointRes = new Point((int) point.getX(), (int) point.getY());

        return pointRes;

    }

    public String getMessage(TopSecretRequest fragments) {
        int maxLength = fragments.getSatellites().stream()
                .mapToInt(fragment -> fragment.getMessage().length)
                .max()
                .orElse(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLength; i++) {
            for (Satellite fragment : fragments.getSatellites()) {
                if (i < fragment.getMessage().length && !fragment.getMessage()[i].isEmpty()) {
                    sb.append(fragment.getMessage()[i]).append(" ");
                }
            }
        }

        return sb.toString().trim();
    }
}
