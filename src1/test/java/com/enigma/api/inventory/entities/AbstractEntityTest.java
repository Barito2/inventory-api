package com.enigma.api.inventory.entities;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractEntityTest {
    @Mock
    private AbstractEntity entity;

    @Test
    void getSet() {
        entity = new AbstractEntity() {
            @Override
            public Object getId() {
                return 1;
            }

            @Override
            public void setId(Object o) {

            }
        };

        entity.setId(1);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setModifiedDate(LocalDateTime.now());

        AbstractEntity actual = new AbstractEntity() {
            @Override
            public Object getId() {
                return 1;
            }

            @Override
            public void setId(Object o) {

            }
        };

        actual.setId(entity.getId());
        actual.setCreatedDate(entity.getCreatedDate());
        actual.setModifiedDate(entity.getModifiedDate());

        assertEquals(entity.getId(), actual.getId());
        assertEquals(entity.getCreatedDate(), actual.getCreatedDate());
        assertEquals(entity.getModifiedDate(), actual.getModifiedDate());
    }
}
