package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.models.items.ItemRequest;
import com.enigma.api.inventory.models.items.ItemResponse;
import com.enigma.api.inventory.models.units.UnitModel;
import com.enigma.api.inventory.services.FileService;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.UnitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService service;

    @MockBean
    private UnitService unitService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private FileService fileService;

    private Unit unit;
    private UnitModel unitModel;
    private Item entity;
    private ItemResponse model;

    @BeforeEach
    void init() {

        unit = new Unit();
        unit.setId(1);
        unit.setCode("xyz");
        unit.setDescription("xyz");
        when(unitService.findById(1)).thenReturn(unit);
        when(unitService.save(any())).thenReturn(unit);

        entity = new Item();
        entity.setId(1);
        entity.setPrice(100);
        entity.setName("Baju");
        entity.setUnit(unit);
        when(service.findById(1)).thenReturn(entity);
        when(service.save(any())).thenReturn(entity);

        unitModel = new UnitModel();
        unitModel.setId(unit.getId());
        unitModel.setCode(unit.getCode());
        unitModel.setDescription(unit.getDescription());

        model = new ItemResponse();
        model.setId(entity.getId());
        model.setPrice(entity.getPrice());
        model.setName(entity.getName());
        model.setUnit(unitModel);

        when(modelMapper.map(any(ItemRequest.class), any(Class.class))).thenReturn(entity);
        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(model);

    }

    @Test
    void addShouldSuccess() throws Exception {

        when(service.save(any())).thenReturn(entity);

        RequestBuilder request =  post("/items").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Baju\", \"price\": \"100\", \"unitId\": \"1\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(entity.getName())));

    }

    @Test
    void findByIdShouldSuccess() throws Exception {

        when(service.findById(1)).thenReturn(entity);

        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(model);

        RequestBuilder request =  get("/items/1").contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(entity.getName())));

    }

    @Test
    void editShouldReturnSuccess() throws Exception {

        Item expected = new Item();
        expected.setName("Celana");
        expected.setPrice(200);
        service.save(expected);

        model = new ItemResponse();
        model.setId(expected.getId());
        model.setName(expected.getName());
        model.setPrice(expected.getPrice());

        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(model);

        RequestBuilder request =  put("/items/1").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Celana\", \"price\": \"200\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(expected.getName())));
    }

    @Test
    void removeByIdShouldSuccess() throws Exception {

        when(service.removeById(1)).thenReturn(entity);

        RequestBuilder request =  delete("/items/1").contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(entity.getName())));

    }

    @Test
    public void findAllShouldReturnList() throws Exception {

        when(service.findAll(any(), anyInt(), anyInt(), any())).thenReturn(Page.empty());

        mvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", is(empty())));
    }

}