package com.enigma.api.inventory.services;


import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.repository.ItemRepository;
import com.enigma.api.inventory.services.impl.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServicesTest {

    @InjectMocks
    private ItemServiceImpl service;

    @Mock
    private ItemRepository repository;

    private Unit unit;

    @BeforeEach
    void init() {
        unit = new Unit();
        unit.setId(1);
        unit.setCode("xyz");
        unit.setDescription("xyz");
    }

    @Test
    void shouldInsertDataTest() {
        Item input = new Item();
        input.setName("x");
        input.setPrice(1);
        input.setUnit(unit);

        Item output = new Item();
        output.setName(input.getName());
        output.setPrice(input.getPrice());
        output.setUnit(input.getUnit());

        when(repository.save(any())).thenReturn(output);

        Item result = service.save(input);

        assertEquals(output, result);
    }

    @Test
    void findByIdShouldReturnEntity() {
        Item output = new Item();
        Mockito.when(repository.findById(Mockito.any()))
                .thenReturn(java.util.Optional.of(output));
        Item result = service.findById(1);
        assertEquals(output, result);
    }

    @Test
    void findAllShouldReturnList() {
        List<Item> expected =  new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(expected);
        List<Item> actual = service.findAll();

        assertEquals(expected, actual);
    }

    @Test
    void removeByIdShouldReturnNull() {
        Item item = service.removeById(1);
        assertEquals(item, null);
    }
}
