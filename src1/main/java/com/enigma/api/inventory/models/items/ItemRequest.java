package com.enigma.api.inventory.models.items;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter @Setter
public class ItemRequest {
    @Size(min = 1, max = 100)
    private String name;
    private Integer price;
    @NonNull
    private Integer unitId;
}
