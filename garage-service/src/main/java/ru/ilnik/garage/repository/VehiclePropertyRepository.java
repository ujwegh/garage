package ru.ilnik.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilnik.garage.model.VehicleProperty;

@Repository
public interface VehiclePropertyRepository extends JpaRepository<VehicleProperty, Long> {
}
