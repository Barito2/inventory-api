package com.enigma.api.inventory.models.stocks;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StockRequest {
    private Integer quantity;
    private Integer itemId;
}
