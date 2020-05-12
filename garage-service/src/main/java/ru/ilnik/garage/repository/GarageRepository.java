package ru.ilnik.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilnik.garage.model.Garage;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {
}
