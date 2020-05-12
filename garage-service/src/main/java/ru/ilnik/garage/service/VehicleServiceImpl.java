package ru.ilnik.garage.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilnik.garage.dto.VehicleDto;
import ru.ilnik.garage.model.Vehicle;
import ru.ilnik.garage.repository.VehicleRepository;

import javax.validation.Valid;

import static ru.ilnik.garage.util.ValidationUtil.checkNotFound;
import static ru.ilnik.garage.util.ValidationUtil.checkNotFoundWithId;

@Slf4j
@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vehicle create(VehicleDto vehicleDto) {
        log.info("Create new vehicle: {}", vehicleDto);
        checkNotFound(vehicleDto, "vehicle must not be null");
        return repository.save(new Vehicle(vehicleDto.getName(), vehicleDto.getType()));
    }

    @Override
    public Vehicle update(@Valid Vehicle vehicle) {
        log.info("Update vehicle with id: {}", vehicle.getId());
        Vehicle existed = getById(vehicle.getId());
        existed.setName(vehicle.getName());
        existed.setType(vehicle.getType());
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
}
