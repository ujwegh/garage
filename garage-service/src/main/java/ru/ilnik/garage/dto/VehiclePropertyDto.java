package ru.ilnik.garage.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VehiclePropertyDto {
    @NotNull
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String value;
}
