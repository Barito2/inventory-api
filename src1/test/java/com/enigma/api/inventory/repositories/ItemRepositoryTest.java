package com.enigma.api.inventory.repositories;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.repository.ItemRepository;
import com.enigma.api.inventory.repository.UnitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository repository;

    @Test
    void shouldInsertDataTest() {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setCode("x");
        unit.setDescription("X");

        Item expected = new Item();
        expected.setName("x");
        expected.setPrice(1);
        expected.setUnit(unit);

        repository.save(expected);
        Item saveItem = repository.findById(expected.getId()).get();

        assertEquals(expected, saveItem);
    }
}
