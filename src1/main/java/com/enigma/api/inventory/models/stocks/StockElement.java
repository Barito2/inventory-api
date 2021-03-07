package com.enigma.api.inventory.models.stocks;

import com.enigma.api.inventory.models.items.ItemResponse;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StockElement {
    private Integer id;
    private Integer quantity;
    private ItemResponse item;
}
