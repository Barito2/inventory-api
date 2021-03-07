package com.enigma.api.inventory.services;


import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.repository.UnitRepository;
import com.enigma.api.inventory.services.impl.UnitServiceImpl;
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
public class UnitServicesTest {

    @InjectMocks
    private UnitServiceImpl service;

    @Mock
    private UnitRepository repository;

    @Test
    void shouldInsertDataTest() {
        Unit input = new Unit();
        input.setCode("x");
        input.setDescription("X");

        Unit output = new Unit();
        output.setId(1);
        output.setCode("x");
        output.setDescription("X");

        when(repository.save(any())).thenReturn(output);

        Unit result = service.save(input);

        assertEquals(output, result);
    }

    @Test
    void findByIdShouldReturnEntity() {
        Unit output = new Unit();
        Mockito.when(repository.findById(Mockito.any()))
                .thenReturn(java.util.Optional.of(output));
        Unit result = service.findById(1);
        assertEquals(output, result);
    }

    @Test
    void findAllShouldReturnList() {
        List<Unit> expected =  new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(expected);
        List<Unit> actual = service.findAll();

        assertEquals(expected, actual);
    }

    @Test
    void removeByIdShouldReturnNull() {
        Unit unit = service.removeById(1);
        assertEquals(unit, null);
    }
}
