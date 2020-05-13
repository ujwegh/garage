package ru.ilnik.garage.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilnik.garage.dto.VehicleDto;
import ru.ilnik.garage.dto.VehiclePropertyDto;
import ru.ilnik.garage.model.Vehicle;
import ru.ilnik.garage.model.VehicleProperty;
import ru.ilnik.garage.repository.VehicleRepository;

import static ru.ilnik.garage.util.ValidationUtil.checkNotFound;
import static ru.ilnik.garage.util.ValidationUtil.checkNotFoundWithId;

@Slf4j
@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repository;
    private final VehiclePropertyService propertyService;

    @Autowired
    public VehicleServiceImpl(VehicleRepository repository, VehiclePropertyService propertyService) {
        this.repository = repository;
        this.propertyService = propertyService;
    }

    @Override
    public Vehicle create(VehicleDto vehicleDto) {
        log.info("Create new vehicle: {}", vehicleDto);
        return repository.save(new Vehicle(vehicleDto.getName(), vehicleDto.getType()));
    }

    @Override
    public Vehicle update(VehicleDto vehicleDto) {
        log.info("Update vehicle with id: {}", vehicleDto.getId());
        Vehicle existed = getById(vehicleDto.getId());
        existed.setName(vehicleDto.getName());
        existed.setType(vehicleDto.getType());
        return repository.save(existed);
    }

    @Override
    public Vehicle getById(long id) {
        log.info("Get vehicle by id: {}", id);
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public void deleteById(long id) {
        log.info("Delete vehicle by id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public Vehicle addProperty(long vehicleId, VehiclePropertyDto dto) {
        log.info("Add new property to vehicle with id: {}", vehicleId);
        Vehicle vehicle = getById(vehicleId);
        vehicle.getProperties().add(propertyService.create(dto));
        return repository.save(vehicle);
    }

    @Override
    public void deleteProperty(long vehicleId, long propertyId) {
        log.info("Delete property with id: {} for vehicle with id: {}", propertyId, vehicleId);
        Vehicle vehicle = getById(vehicleId);
        vehicle.getProperties().removeIf(vehicleProperty -> vehicleProperty.getId() == propertyId);
        repository.save(vehicle);
        propertyService.deleteById(propertyId);
    }
}
