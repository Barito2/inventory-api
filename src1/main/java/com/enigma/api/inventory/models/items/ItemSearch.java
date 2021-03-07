package com.enigma.api.inventory.models.items;

import com.enigma.api.inventory.models.PageSearch;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearch extends PageSearch {
    private String name;
}
