package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.entities.Transaction;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.models.items.ItemResponse;
import com.enigma.api.inventory.models.stocks.StockRequest;
import com.enigma.api.inventory.models.stocks.StockResponse;
import com.enigma.api.inventory.models.transaction.TransactionRequest;
import com.enigma.api.inventory.models.transaction.TransactionResponse;
import com.enigma.api.inventory.models.units.UnitModel;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.StockService;
import com.enigma.api.inventory.services.TransactionService;
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

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UnitService unitService;

    @MockBean
    private ItemService itemService;

    @MockBean
    private StockService stockService;

    @MockBean
    private TransactionService service;

    @MockBean
    private ModelMapper modelMapper;

    private Unit unit;
    private UnitModel unitModel;
    private Item item;
    private ItemResponse itemResponse;
    private Stock entityStock;
    private StockResponse modelStock;
    private Transaction entity;
    private TransactionResponse model;

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

        entityStock = new Stock();
        entityStock.setId(1);
        entityStock.setQuantity(100);
        entityStock.setItem(item);
        when(stockService.findById(1)).thenReturn(entityStock);
        when(stockService.save(any())).thenReturn(entityStock);

        entity = new Transaction();
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

        modelStock = new StockResponse();
        modelStock.setId(entityStock.getId());
        modelStock.setQuantity(entityStock.getQuantity());
        modelStock.setItem(itemResponse);

        model = new TransactionResponse();
        model.setId(entity.getId());
        model.setQuantity(entity.getQuantity().intValue());
        model.setItem(itemResponse);

        when(modelMapper.map(any(StockRequest.class), any(Class.class))).thenReturn(entityStock);
        when(modelMapper.map(any(Stock.class), any(Class.class))).thenReturn(modelStock);

        when(modelMapper.map(any(TransactionRequest.class), any(Class.class))).thenReturn(entity);
        when(modelMapper.map(any(Transaction.class), any(Class.class))).thenReturn(model);
    }

    @Test
    void addShouldContentTypeJson() throws Exception {
        RequestBuilder request =  post("/transaction").contentType(MediaType.APPLICATION_JSON)
                .content("{\"quantity\": \"1\", \"itemId\": \"1\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void findAllShouldReturnList() throws Exception {
        when(service.findAll(any(), anyInt(), anyInt(), any())).thenReturn(Page.empty());

        mvc.perform(get("/transaction"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", is(empty())));
    }
}