package com.pruebatecnica.operacionfuegoquasar.domain.usecase.topsecret;

import com.pruebatecnica.operacionfuegoquasar.domain.model.Satellite;
import com.pruebatecnica.operacionfuegoquasar.domain.model.TopSecretRequest;

import java.awt.*;
import java.util.List;

public interface ITopSecretUseCase {

    Point getLocation(TopSecretRequest satellites);

    String getMessage(List<Satellite> satellites);

}
