package com.enigma.api.inventory.models.units;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UnitModel {
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 10)
    private String code;

    @NotBlank
    @Size(min = 1, max = 100)
    private String description;
}
