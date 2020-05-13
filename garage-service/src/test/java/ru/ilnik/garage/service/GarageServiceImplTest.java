package ru.ilnik.garage.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ilnik.garage.dto.VehicleDto;
import ru.ilnik.garage.model.Garage;
import ru.ilnik.garage.model.Vehicle;
import ru.ilnik.garage.model.enums.VehicleType;
import ru.ilnik.garage.repository.GarageRepository;
import ru.ilnik.garage.repository.VehicleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GarageServiceImplTest {

    @Autowired
    private GarageService garageService;

    @Autowired
    private GarageRepository repository;

    @Autowired
    private VehicleRepository vehicleRepository;
    private Garage expected;
    private final List<Vehicle> vehicles = new ArrayList<>();
    @BeforeEach
    void init() {
        vehicles.add(vehicleRepository.save(new Vehicle("truck", VehicleType.TRUCK)));
        vehicles.add(vehicleRepository.save(new Vehicle("car", VehicleType.CAR)));
        Garage garage = new Garage(1L, 57.64911, 10.40744);
        garage.setVehicles(vehicles);
        expected = repository.save(garage);
    }

    @Test
    void create() {
        Garage garage = garageService.create(1L);
        assertNotNull(garage);
        List<Garage> all = repository.findAll();
        assertTrue(all.size() > 1);
        assertEquals(2, all.size());
    }

    @Test
    void getById() {
        Garage actual = garageService.getById(expected.getId());
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getLatitude(), actual.getLatitude());
        assertEquals(expected.getLongitude(), actual.getLongitude());
        assertEquals(expected.getUserId(), actual.getUserId());
    }

    @Test
    void deleteById() {
        garageService.deleteById(expected.getId());
        assertTrue(repository.findAll().isEmpty());
        assertEquals(0, repository.findAll().size());
        List<Vehicle> vehicles = vehicleRepository.findAll();
        assertNotNull(vehicles);
        assertEquals(0, vehicles.size());
    }

    @Test
    void update() {
        expected.setLongitude(99.99);
        Garage actual = garageService.update(expected);
        assertNotNull(actual);
        assertEquals(99.99, actual.getLongitude());
        assertSame(expected.getId(), actual.getId());
        assertSame(expected.getLatitude(), actual.getLatitude());
        assertSame(expected.getLongitude(), actual.getLongitude());
        assertSame(expected.getUserId(), actual.getUserId());
    }

    @Test
    void getAllByUserId() {
        List<Garage> garages = garageService.getAllByUserId(1L);
        assertNotNull(garages);
        assertTrue(garages.size() > 0);
        Garage actual = garages.stream().findFirst().orElse(null);
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getLatitude(), actual.getLatitude());
        assertEquals(expected.getLongitude(), actual.getLongitude());
        assertEquals(expected.getUserId(), actual.getUserId());
    }

    @Test
    void addVehicle() {
        VehicleDto vehicleDto = new VehicleDto("aircraft", VehicleType.AIRCRAFT);
        Garage actual = garageService.addVehicle(expected.getId(), vehicleDto);
        assertNotNull(actual);
        assertNotNull(actual.getVehicles());
        assertEquals(3, actual.getVehicles().size());
        List<Vehicle> vehicles = vehicleRepository.findAll();
        assertNotNull(vehicles);
        assertEquals(3, vehicles.size());
    }

    @Test
    void deleteVehicle() {
        Vehicle vehicle = vehicles.stream().findFirst().orElse(null);
        assertNotNull(vehicle);
        garageService.deleteVehicle(expected.getId(), vehicle.getId());
        Garage garage = repository.findById(expected.getId()).orElse(null);
        assertNotNull(garage);
        assertEquals(1, garage.getVehicles().size());
        List<Vehicle> vehicles = vehicleRepository.findAll();
        assertNotNull(vehicles);
        assertTrue(vehicles.size() > 0);
        assertEquals(1, vehicles.size());
    }
}