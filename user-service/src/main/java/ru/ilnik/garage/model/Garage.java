package ru.ilnik.garage.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "garages")
@EqualsAndHashCode(callSuper = true)
public class Garage extends BaseEntity {

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();
}
