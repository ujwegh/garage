package ru.ilnik.garage.service;

import ru.ilnik.garage.dto.VehiclePropertyDto;
import ru.ilnik.garage.model.VehicleProperty;

public interface VehiclePropertyService {
    VehicleProperty create(VehiclePropertyDto propertyDto);
    VehicleProperty getById(long vehPropId);
    VehicleProperty update(VehiclePropertyDto vehiclePropertyDto);
    void deleteById(long vehiclePropId);
}
