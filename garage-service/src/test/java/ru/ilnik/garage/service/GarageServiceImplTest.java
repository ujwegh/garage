package ru.ilnik.garage.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ilnik.garage.model.Garage;
import ru.ilnik.garage.repository.GarageRepository;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GarageServiceImplTest {

    @Autowired
    private GarageService garageService;

    @Autowired
    private GarageRepository repository;

    private static final Garage garage = new Garage(1L, 57.64911, 10.40744);

    @BeforeEach
    void init() {
        repository.save(garage);
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
        Garage garage = getGarage();
        Garage actual = garageService.getById(garage.getId());
        assertNotNull(actual);
        assertEquals(garage.getId(), actual.getId());
        assertEquals(garage.getLatitude(), actual.getLatitude());
        assertEquals(garage.getLongitude(), actual.getLongitude());
        assertEquals(garage.getUserId(), actual.getUserId());
    }

    @Test
    void deleteById() {
        Garage garage = getGarage();
        garageService.deleteById(garage.getId());
        assertTrue(repository.findAll().isEmpty());
        assertEquals(0, repository.findAll().size());
    }

    @Test
    void update() {
        Garage expected = getGarage();
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
        Garage expected = getGarage();
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

    private Garage getGarage() {
        Garage garage = repository.findAll().stream().findFirst().orElse(null);
        assertNotNull(garage);
        return garage;
    }
}