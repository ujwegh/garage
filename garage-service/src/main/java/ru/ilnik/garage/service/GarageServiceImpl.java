package ru.ilnik.garage.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilnik.garage.model.Garage;
import ru.ilnik.garage.repository.GarageRepository;

import javax.transaction.Transactional;
import java.util.List;

import static ru.ilnik.garage.util.ValidationUtil.checkNotFoundWithId;

@Slf4j
@Service
@Transactional
public class GarageServiceImpl implements GarageService {

    private final GarageRepository repository;

    @Autowired
    public GarageServiceImpl(GarageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Garage create(long userId) {
        log.debug("Create new garage for user with id: {}", userId);
        return repository.save(new Garage(userId));
    }

    @Override
    public Garage getById(long garageId) {
        log.debug("Find garage by id: {}", garageId);
        return checkNotFoundWithId(repository.findById(garageId).orElse(null), garageId);
    }

    @Override
    public void deleteById(long garageId) {
        log.debug("Delete garage by id: {}", garageId);
        repository.deleteById(garageId);
    }

    @Override
    public Garage update(Garage garage) {
        checkNotFoundWithId(garage, garage.getId());
        log.debug("Update garage with id: {}", garage.getId());
        Garage existed = getById(garage.getId());
        existed.setLatitude(garage.getLatitude());
        existed.setLongitude(garage.getLongitude());
        return repository.save(existed);
    }

    @Override
    public List<Garage> getAllByUserId(long userId) {
        log.debug("Find all by user id: {}", userId);
        return repository.findAll();
    }
}
