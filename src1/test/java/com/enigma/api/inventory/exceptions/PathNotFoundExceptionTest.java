package com.enigma.api.inventory.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathNotFoundExceptionTest {
    @Test
    void PathNotFoundTest() {
        PathNotFoundException actual = new PathNotFoundException();
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatus());
    }
}
