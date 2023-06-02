package com.pruebatecnica.operacionfuegoquasar.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

@Data
@AllArgsConstructor
public class TopSecretResponse {
    private Point location;
    private String message;
}
