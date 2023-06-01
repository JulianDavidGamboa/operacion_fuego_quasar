package com.pruebatecnica.operacionfuegoquasar.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class TopSecretRequest {
    private List<Satellite> satellites;
}
