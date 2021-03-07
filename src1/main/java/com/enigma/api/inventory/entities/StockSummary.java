package com.enigma.api.inventory.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
public class StockSummary {
    private Item item;
    private Long quantity;
    private Long totalPrice;
}
