package ru.ilnik.garage.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.ilnik.garage.model.enums.VehicleType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "vehicles")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Vehicle extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VehicleType type;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL)
    private List<VehicleProperty> properties;

    public Vehicle(String name, VehicleType type) {
        this.name = name;
        this.type = type;
    }
}
