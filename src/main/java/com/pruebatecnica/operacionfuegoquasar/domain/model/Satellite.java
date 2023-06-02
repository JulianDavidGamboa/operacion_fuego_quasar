package com.pruebatecnica.operacionfuegoquasar.domain.model;

import lombok.Data;

@Data
public class Satellite {
    private String name;
    private double distance;
    private String[] message;

    public Satellite(String name, double distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }
}
