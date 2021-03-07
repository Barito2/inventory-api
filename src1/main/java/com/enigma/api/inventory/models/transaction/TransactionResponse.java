package com.enigma.api.inventory.models.transaction;

import com.enigma.api.inventory.models.items.ItemResponse;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransactionResponse {
    private Integer id;
    private Integer quantity;
    private Integer TotalPrice;
    private ItemResponse item;
}
