package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.models.items.ItemResponse;
import com.enigma.api.inventory.models.stocks.StockRequest;
import com.enigma.api.inventory.models.stocks.StockResponse;
import com.enigma.api.inventory.models.units.UnitModel;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.StockService;
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

@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UnitService unitService;

    @MockBean
    private ItemService itemService;

    @MockBean
    private StockService service;

    @MockBean
    private ModelMapper modelMapper;

    private Unit unit;
    private UnitModel unitModel;
    private Item item;
    private ItemResponse itemResponse;
    private Stock entity;
    private StockResponse model;

    @BeforeEach
    void init() {
        unit = new Unit();
        unit.setId(1);
        unit.setCode("xyz");
        unit.setDescription("xyz");
        when(unitService.findById(1)).thenReturn(unit);

        item = new Item();
        item.setId(1);
        item.setPrice(100);
        item.setName("Baju");
        item.setUnit(unit);
        when(itemService.findById(1)).thenReturn(item);

        entity = new Stock();
        entity.setId(1);
        entity.setQuantity(100);
        entity.setItem(item);
        when(service.findById(1)).thenReturn(entity);
        when(service.save(any())).thenReturn(entity);

        unitModel = new UnitModel();
        unitModel.setId(unit.getId());
        unitModel.setCode(unit.getCode());
        unitModel.setDescription(unit.getDescription());

        itemResponse = new ItemResponse();
        itemResponse.setId(item.getId());
        itemResponse.setPrice(item.getPrice());
        itemResponse.setName(item.getName());
        itemResponse.setUnit(unitModel);

        model = new StockResponse();
        model.setId(entity.getId());
        model.setQuantity(entity.getQuantity());
        model.setItem(itemResponse);

        when(modelMapper.map(any(StockRequest.class), any(Class.class))).thenReturn(entity);
        when(modelMapper.map(any(Stock.class), any(Class.class))).thenReturn(model);

    }

    @Test
    void addShouldSuccess() throws Exception {
        RequestBuilder request =  post("/stocks").contentType(MediaType.APPLICATION_JSON)
                .content("{\"quantity\": \"100\", \"itemId\": \"1\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(entity.getQuantity())));
    }

    @Test
    void findByIdShouldSuccess() throws Exception {

        when(modelMapper.map(any(Stock.class), any(Class.class))).thenReturn(model);

        RequestBuilder request =  get("/stocks/1").contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(entity.getQuantity())));

    }

    @Test
    void editShouldReturnSuccess() throws Exception {

        Stock expected = new Stock();
        expected.setId(1);
        expected.setQuantity(500);
        expected.setItem(item);
        service.save(expected);

        model = new StockResponse();
        model.setId(expected.getId());
        model.setQuantity(expected.getQuantity());
        model.setItem(itemResponse);

        when(modelMapper.map(any(Stock.class), any(Class.class))).thenReturn(model);

        RequestBuilder request = put("/stocks/1").contentType(MediaType.APPLICATION_JSON)
                .content("{\"quantity\": \"500\", \"itemId\": \"1\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(expected.getQuantity())));
    }

    @Test
    void removeByIdShouldSuccess() throws Exception {
        when(service.removeById(1)).thenReturn(entity);

        RequestBuilder request =  delete("/stocks/1").contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(entity.getQuantity())));

    }

    @Test
    public void findAllShouldReturnList() throws Exception {

        when(service.findAll(any(), anyInt(), anyInt(), any())).thenReturn(Page.empty());

        mvc.perform(get("/stocks"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", is(empty())));
    }
}