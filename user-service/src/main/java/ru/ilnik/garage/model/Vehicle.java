package ru.ilnik.garage.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.ilnik.garage.model.enums.VehicleType;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "vehicles")
@EqualsAndHashCode(callSuper = true)
public class Vehicle extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VehicleType type;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL)
    private List<VehicleProperty> properties;

}
