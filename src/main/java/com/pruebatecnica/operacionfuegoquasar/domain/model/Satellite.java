package com.pruebatecnica.operacionfuegoquasar.domain.model;

import lombok.Data;

@Data
public class Satellite {
    private String name;
    private double distance;
    private String[] message;
}
