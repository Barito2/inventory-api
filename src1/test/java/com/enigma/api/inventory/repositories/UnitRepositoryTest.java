package com.enigma.api.inventory.repositories;

import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.repository.UnitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UnitRepositoryTest {

    @Autowired
    private UnitRepository repository;

    @Test
    void shouldInsertDataTest() {
        Unit expected = new Unit();
        expected.setCode("x");
        expected.setDescription("X");

        repository.save(expected);
        Unit saveUnit = repository.findById(expected.getId()).get();

        assertEquals(expected, saveUnit);
    }
}
