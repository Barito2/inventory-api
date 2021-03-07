package com.enigma.api.inventory.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

import javax.validation.constraints.Max;

@Getter @Setter
public class PageSearch {
    private Integer page = 0;

    @Max(100)
    private Integer size = 10;

    private Direction sort = Direction.ASC;
}
