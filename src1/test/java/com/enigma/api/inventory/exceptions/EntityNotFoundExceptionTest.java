package com.enigma.api.inventory.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityNotFoundExceptionTest{
    @Test
    void EntityNotFoundExceptionTest() {
        EntityNotFoundException actual = new EntityNotFoundException();
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatus());
    }
}
