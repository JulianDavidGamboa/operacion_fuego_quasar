package com.pruebatecnica.operacionfuegoquasar.domain.model;

import lombok.Data;

import java.awt.*;

@Data
public class TopSecretResponse {
    private Point location;
    private String message;
}
