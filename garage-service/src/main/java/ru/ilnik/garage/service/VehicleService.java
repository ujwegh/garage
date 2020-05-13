package ru.ilnik.garage.service;

import ru.ilnik.garage.dto.VehicleDto;
import ru.ilnik.garage.dto.VehiclePropertyDto;
import ru.ilnik.garage.model.Vehicle;

public interface VehicleService {
    Vehicle create(VehicleDto vehicleDto);
    Vehicle update(VehicleDto VehicleDto);
    Vehicle getById(long id);
    void deleteById(long id);
    Vehicle addProperty(long vehicleId, VehiclePropertyDto dto);
    void deleteProperty(long vehicleId, long propertyId);
}
