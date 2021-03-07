package com.enigma.api.inventory.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import javax.persistence.Table;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationExceptionTest {
    @Test
    void ApplicationExceptionShouldOkStatus() {
        ApplicationException actual = new ApplicationException(HttpStatus.OK, "ok");
        assertEquals(HttpStatus.OK, actual.getStatus());
    }
}
