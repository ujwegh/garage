package ru.ilnik.garage.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "garages")
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class Garage extends BaseEntity {

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles = new ArrayList<>();

    public Garage(@NotNull Long userId) {
        this.userId = userId;
    }

    public Garage(Long userId, Double longitude, Double latitude) {
        this(userId);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
