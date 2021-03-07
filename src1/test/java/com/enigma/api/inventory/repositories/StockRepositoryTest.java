package com.enigma.api.inventory.repositories;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.repository.ItemRepository;
import com.enigma.api.inventory.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class StockRepositoryTest {

    @Autowired
    private StockRepository repository;

    @Test
    void shouldInsertDataTest() {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setCode("x");
        unit.setDescription("X");

        Item item = new Item();
        item.setId(1);
        item.setName("x");
        item.setPrice(1);
        item.setUnit(unit);

        Stock expected = new Stock();
        expected.setQuantity(1);
        expected.setTotalPrice(1);
        expected.setItem(item);
        
        repository.save(expected);
        Stock saveStock = repository.findById(expected.getId()).get();

        assertEquals(expected, saveStock);
    }
}
