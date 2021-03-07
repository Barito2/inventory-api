package com.enigma.api.inventory.services;


import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.repository.StockRepository;
import com.enigma.api.inventory.services.impl.StockServiceImpl;
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
public class StockServicesTest {

    @InjectMocks
    private StockServiceImpl service;

    @Mock
    private StockRepository repository;

    private Unit unit;
    private Item item;

    @BeforeEach
    void init() {
        unit = new Unit();
        unit.setId(1);
        unit.setCode("xyz");
        unit.setDescription("xyz");

        item = new Item();
        item.setName("x");
        item.setPrice(1);
        item.setUnit(unit);
    }

    @Test
    void shouldInsertDataTest() {
        Stock input = new Stock();
        input.setQuantity(1);
        input.setTotalPrice(1);
        input.setItem(item);

        Stock output = new Stock();
        output.setQuantity(input.getQuantity());
        output.setTotalPrice(input.getTotalPrice());
        output.setItem(input.getItem());

        when(repository.save(any())).thenReturn(output);

        Stock result = service.save(input);

        assertEquals(output, result);
    }

    @Test
    void findByIdShouldReturnEntity() {
        Stock output = new Stock();
        Mockito.when(repository.findById(Mockito.any()))
                .thenReturn(java.util.Optional.of(output));
        Stock result = service.findById(1);
        assertEquals(output, result);
    }

    @Test
    void findAllShouldReturnList() {
        List<Stock> expected =  new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(expected);
        List<Stock> actual = service.findAll();

        assertEquals(expected, actual);
    }

    @Test
    void removeByIdShouldReturnNull() {
        Stock stock = service.removeById(1);
        assertEquals(stock, null);
    }
}
