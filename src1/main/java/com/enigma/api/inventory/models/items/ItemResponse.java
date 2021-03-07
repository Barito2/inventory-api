package com.enigma.api.inventory.models.items;

import com.enigma.api.inventory.models.units.UnitModel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemResponse {
    private Integer id;
    private String name;
    private Integer price;
    private UnitModel unit;
}
