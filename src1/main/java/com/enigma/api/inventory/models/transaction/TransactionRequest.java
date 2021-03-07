package com.enigma.api.inventory.models.transaction;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransactionRequest {
    private Integer quantity;
    private Integer itemId;
}
