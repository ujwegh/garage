package ru.ilnik.garage.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.ilnik.garage.dto.VehicleDto;
import ru.ilnik.garage.model.Vehicle;
import ru.ilnik.garage.model.enums.VehicleType;
import ru.ilnik.garage.repository.VehicleRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class VehicleServiceImplTest {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository repository;

    private static final Vehicle car = new Vehicle("car", VehicleType.CAR);
    private static final Vehicle motorcycle = new Vehicle("motorcycle", VehicleType.MOTO);

    @BeforeEach
    void init() {
        repository.save(car);
        repository.save(motorcycle);
    }

    @Test
    void create() {
        VehicleDto dto = new VehicleDto("aircraft", VehicleType.AIRCRAFT);
        Vehicle actual = vehicleService.create(dto);
        assertNotNull(actual);
        List<Vehicle> all = repository.findAll();
        assertNotNull(all);
        assertEquals(3, all.size());
        assertTrue(all.contains(actual));
    }

    @Test
    void update() {
        Vehicle expected = getVehicle();
        VehicleDto vehicleDto = new VehicleDto(expected.getId(), "new Name", VehicleType.TRUCK);
        Vehicle actual = vehicleService.update(vehicleDto);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void getById() {
        Vehicle expected = getVehicle();
        Vehicle actual = vehicleService.getById(expected.getId());
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        Vehicle vehicle = getVehicle();
        vehicleService.deleteById(vehicle.getId());
        List<Vehicle> all = repository.findAll();
        assertNotNull(all);
        assertTrue(all.size() > 0);
        assertNotSame(vehicle, all.stream().findFirst());
    }

    private Vehicle getVehicle() {
        Vehicle vehicle = repository.findAll().stream().findFirst().orElse(null);
        assertNotNull(vehicle);
        return vehicle;
    }


}