package ru.ilnik.garage.service;

import ru.ilnik.garage.dto.VehicleDto;
import ru.ilnik.garage.model.Vehicle;

public interface VehicleService {
    Vehicle create(VehicleDto vehicleDto);
    Vehicle update(Vehicle vehicle);
    Vehicle getById(long id);
    void deleteById(long id);
}
