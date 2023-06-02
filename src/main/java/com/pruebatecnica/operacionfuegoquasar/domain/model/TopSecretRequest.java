package com.pruebatecnica.operacionfuegoquasar.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TopSecretRequest {
    private List<Satellite> satellites;
}
