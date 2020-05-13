package ru.ilnik.garage.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilnik.garage.dto.VehicleDto;
import ru.ilnik.garage.model.Garage;
import ru.ilnik.garage.model.Vehicle;
import ru.ilnik.garage.repository.GarageRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static ru.ilnik.garage.util.ValidationUtil.checkNotFoundWithId;

@Slf4j
@Service
@Transactional
public class GarageServiceImpl implements GarageService {

    private final GarageRepository repository;
    private final VehicleService vehicleService;

    @Autowired
    public GarageServiceImpl(GarageRepository repository, VehicleService vehicleService) {
        this.repository = repository;
        this.vehicleService = vehicleService;
    }

    @Override
    public Garage create(long userId) {
        log.info("Create new garage for user with id: {}", userId);
        return repository.save(new Garage(userId));
    }

    @Override
    public Garage getById(long garageId) {
        log.info("Find garage by id: {}", garageId);
        return checkNotFoundWithId(repository.findById(garageId).orElse(null), garageId);
    }

    @Override
    public void deleteById(long garageId) {
        log.info("Delete garage by id: {}", garageId);
        repository.deleteById(garageId);
    }

    @Override
    public Garage update(Garage garage) {
        checkNotFoundWithId(garage, garage.getId());
        log.info("Update garage with id: {}", garage.getId());
        Garage existed = getById(garage.getId());
        existed.setLatitude(garage.getLatitude());
        existed.setLongitude(garage.getLongitude());
        return repository.save(existed);
    }

    @Override
    public List<Garage> getAllByUserId(long userId) {
        log.info("Find all by user id: {}", userId);
        return repository.findAll();
    }

    @Override
    public Garage addVehicle(long garageId, VehicleDto dto) {
        log.info("Add new vehicle {} to garage with id: {}", dto, garageId);
        Garage garage = getById(garageId);
        garage.getVehicles().add(vehicleService.create(dto));
        return repository.save(garage);
    }

    @Override
    public void deleteVehicle(long garageId, long vehicleId) {
        log.info("Delete vehicle with id: {} for garage with id: {}", vehicleId, garageId);
        Garage garage = getById(garageId);
        garage.getVehicles().removeIf(vehicle -> vehicle.getId() == vehicleId);
        repository.save(garage);
        vehicleService.deleteById(vehicleId);
    }
}
