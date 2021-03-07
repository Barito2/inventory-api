package com.enigma.api.inventory.models.stocks;

import com.enigma.api.inventory.models.PageSearch;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StockSearch extends PageSearch {
    private Integer id;
    private Integer quantity;
}
