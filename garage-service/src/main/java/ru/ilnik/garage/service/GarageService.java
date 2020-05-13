package ru.ilnik.garage.service;

import ru.ilnik.garage.dto.VehicleDto;
import ru.ilnik.garage.model.Garage;

import java.util.List;

public interface GarageService {
    Garage create(long userId);
    Garage getById(long garageId);
    void deleteById(long garageId);
    Garage update(Garage garage);
    List<Garage> getAllByUserId(long userId);
    Garage addVehicle(long garageId, VehicleDto dto);
    void deleteVehicle(long garageId, long vehicleId);
}
