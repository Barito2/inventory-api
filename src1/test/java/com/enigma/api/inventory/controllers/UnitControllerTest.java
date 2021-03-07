package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.models.units.UnitModel;
import com.enigma.api.inventory.models.units.UnitSearch;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UnitController.class)
public class UnitControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UnitService service;

    @MockBean
    private ModelMapper modelMapper;

    private Unit entity;
    private UnitModel model;

    @BeforeEach
    void init() {
        entity = new Unit();
        entity.setId(1);
        entity.setCode("xyz");
        entity.setDescription("xyz");

        model = new UnitModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setDescription(entity.getDescription());

        when(service.findById(anyInt())).thenReturn(entity);
        when(service.save(any())).thenReturn(entity);

        when(modelMapper.map(any(UnitModel.class), any(Class.class))).thenReturn(entity);
        when(modelMapper.map(any(Unit.class), any(Class.class))).thenReturn(model);
    }

    @Test
    void addShouldSuccess() throws Exception {
        RequestBuilder request =  post("/units").contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"xyz\", \"description\": \"xyz\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(model.getCode())));
    }

    @Test
    void findAllShouldReturnList () throws Exception {
        when(service.findAll(any(), anyInt(), anyInt(), any())).thenReturn(Page.empty());

        mvc.perform(MockMvcRequestBuilders.get("/units"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }

    @Test
    void findByIdShouldReturnEntity() throws Exception {
        when(service.findById(anyInt())).thenReturn(entity);

        mvc.perform(MockMvcRequestBuilders.get("/units/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(entity.getCode())));
    }

    @Test
    void editShouldSuccess() throws Exception{
        RequestBuilder request =  put("/units/1").contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"xyz\", \"description\": \"xyz\"}");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(model.getCode())));
    }

    @Test
    void removeByIdShouldSuccess() throws Exception {
        when(service.removeById(anyInt())).thenReturn(entity);

        mvc.perform(MockMvcRequestBuilders.delete("/units/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(entity.getCode())));
    }
}
