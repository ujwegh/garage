package ru.ilnik.garage.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "properties")
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class VehicleProperty extends BaseEntity{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value")
    private String value;
}
