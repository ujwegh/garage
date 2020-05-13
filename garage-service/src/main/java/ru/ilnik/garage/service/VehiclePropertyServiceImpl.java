package ru.ilnik.garage.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilnik.garage.dto.VehiclePropertyDto;
import ru.ilnik.garage.model.Vehicle;
import ru.ilnik.garage.model.VehicleProperty;
import ru.ilnik.garage.repository.VehiclePropertyRepository;

import static ru.ilnik.garage.util.ValidationUtil.checkNotFound;
import static ru.ilnik.garage.util.ValidationUtil.checkNotFoundWithId;

@Slf4j
@Service
public class VehiclePropertyServiceImpl implements VehiclePropertyService {

    private final VehiclePropertyRepository repository;

    @Autowired
    public VehiclePropertyServiceImpl(VehiclePropertyRepository repository) {
        this.repository = repository;
    }

    @Override
    public VehicleProperty create(VehiclePropertyDto propertyDto) {
        log.info("Create new vehicle property: {}", propertyDto);
        return repository.save(new VehicleProperty(propertyDto.getName(), propertyDto.getValue()));
    }

    @Override
    public VehicleProperty getById(long vehPropId) {
        log.info("Get vehicle property with id: {}", vehPropId);
        return checkNotFoundWithId(repository.findById(vehPropId).orElse(null), vehPropId);
    }

    @Override
    public VehicleProperty update(VehiclePropertyDto dto) {
        log.info("Update vehicle property with id: {}", dto.getId());
        VehicleProperty existed = getById(dto.getId());
        existed.setName(dto.getName());
        existed.setValue(dto.getValue());
        return repository.save(existed);
    }

    @Override
    public void deleteById(long vehiclePropId) {
        log.info("Delete vehicle property with id: {}", vehiclePropId);
        repository.deleteById(vehiclePropId);
    }
}
