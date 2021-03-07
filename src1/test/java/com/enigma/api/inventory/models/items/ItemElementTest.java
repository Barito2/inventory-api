package com.enigma.api.inventory.models.items;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.models.items.ItemElement;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemElementTest {

    @Mock
    ItemElement item;

    @Mock
    Unit unit;

    @Test
    void getSet() {
        item = new ItemElement();
        item.setId(1);
        item.setName("x");
        item.setPrice(1);

        ItemElement actual = new ItemElement();
        actual.setId(1);
        actual.setName("x");
        actual.setPrice(1);

        assertEquals(item.getId(), actual.getId());
        assertEquals(item.getName(), actual.getName());
        assertEquals(item.getPrice(), actual.getPrice());
    }
}
