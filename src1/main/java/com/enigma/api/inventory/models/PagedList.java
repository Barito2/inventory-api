package com.enigma.api.inventory.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
public class PagedList<T> {
    private List<T> list;
    private Integer page;
    private Integer size;
    private Long total;
}
