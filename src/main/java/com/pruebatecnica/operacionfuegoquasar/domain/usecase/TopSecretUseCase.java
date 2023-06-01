package com.pruebatecnica.operacionfuegoquasar.domain.usecase;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.pruebatecnica.operacionfuegoquasar.domain.model.Satellite;
import com.pruebatecnica.operacionfuegoquasar.domain.model.TopSecretRequest;
import com.pruebatecnica.operacionfuegoquasar.infrastructure.drivenadapter.SatelliteRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import java.awt.Point;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;
import java.util.List;

@Component
@AllArgsConstructor
public class TopSecretUseCase {

    private static Logger log = LoggerFactory.getLogger(TopSecretUseCase.class);

    @Autowired
    private SatelliteRepository satelliteRepository;

    public Point getLocation(TopSecretRequest satellites) {

        double x1 = satelliteRepository.getKenobiPosition().getX();
        double y1 = satelliteRepository.getKenobiPosition().getY();
        double x2 = satelliteRepository.getSkywalkerPosition().getX();
        double y2 = satelliteRepository.getSkywalkerPosition().getY();
        double x3 = satelliteRepository.getSatoPosition().getX();
        double y3 = satelliteRepository.getSatoPosition().getY();

        double[] satellitesDisntaces = satellites.getSatellites().stream().mapToDouble(Satellite::getDistance).toArray();

        double r1 = satellitesDisntaces[0];
        double r2 = satellitesDisntaces[1];
        double r3 = satellitesDisntaces[2];

        /**
        double [][] positions = new double[][]{{ x1, y1 },{ x2, y2 },{ x3, y3 }};

        double[] distances = new double[]{r1, r2, r3};

        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
        Optimum optimum = solver.solve();

        return optimum.getPoint().toArray();
         */


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
