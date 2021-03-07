package com.enigma.api.inventory.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StockSummaryTest {
    @Mock
    Item item;

    @Mock
    Unit unit;

    @Mock
    StockSummary summary;

    @Test
    void getSet() {
        unit = new Unit();
        unit.setId(1);
        unit.setCode("x");
        unit.setDescription("X");

        item = new Item();
        item.setId(1);
        item.setName("x");
        item.setPrice(1);
        item.setUnit(unit);

        summary = new StockSummary(item, 12L ,12L);

        StockSummary actual = new StockSummary(item, 12L ,12L);

        assertEquals(summary.getItem(), actual.getItem());
        assertEquals(summary.getQuantity(), actual.getQuantity());
        assertEquals(summary.getTotalPrice(), actual.getTotalPrice());
    }

}
