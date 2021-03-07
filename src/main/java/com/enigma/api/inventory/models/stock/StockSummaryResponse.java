package com.enigma.api.inventory.models.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockSummaryResponse {

    private Integer itemId;
    private String itemName;
    private Integer itemPrice;
    private String imageUrl;
    private Long quantity;

}
