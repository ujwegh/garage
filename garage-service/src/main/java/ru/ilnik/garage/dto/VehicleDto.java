package ru.ilnik.garage.dto;

import lombok.*;
import ru.ilnik.garage.model.enums.VehicleType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class VehicleDto {
    @NotNull
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private VehicleType type;

    public VehicleDto(@NotNull @NotBlank String name, @NotNull VehicleType type) {
        this.name = name;
        this.type = type;
    }
}
