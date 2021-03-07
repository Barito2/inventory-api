package com.enigma.api.inventory.models.items;

import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.models.items.ItemElement;
import com.enigma.api.inventory.models.items.ItemSearch;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemSearchTest {

    @Mock
    ItemSearch item;

    @Mock
    Unit unit;

    @Test
    void getSet() {
        item = new ItemSearch();
        item.setName("x");

        ItemSearch actual = new ItemSearch();
        actual.setName("x");

        assertEquals(item.getName(), actual.getName());
    }
}
