package ru.ilnik.garage.service;

import ru.ilnik.garage.model.Garage;

import java.util.List;

public interface GarageService {
    Garage create(long userId);
    Garage getById(long garageId);
    void deleteById(long garageId);
    Garage update(Garage garage);
    List<Garage> getAllByUserId(long userId);
}
