package ru.ilnik.garage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.ilnik.garage.model.enums.VehicleType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class VehicleDto {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private VehicleType type;
}
