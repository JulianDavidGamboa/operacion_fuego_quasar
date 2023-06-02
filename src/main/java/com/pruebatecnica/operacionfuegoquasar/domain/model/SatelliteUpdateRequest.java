package com.pruebatecnica.operacionfuegoquasar.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class SatelliteUpdateRequest {
    private double distance;
    private String[] message;
}
