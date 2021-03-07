package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.entities.Transaction;
import com.enigma.api.inventory.messages.ResponseMessage;
import com.enigma.api.inventory.models.PagedList;
import com.enigma.api.inventory.models.stocks.StockRequest;
import com.enigma.api.inventory.models.transaction.TransactionElement;
import com.enigma.api.inventory.models.transaction.TransactionRequest;
import com.enigma.api.inventory.models.transaction.TransactionResponse;
import com.enigma.api.inventory.models.transaction.TransactionSearch;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.StockService;
import com.enigma.api.inventory.services.TransactionService;
import com.enigma.api.inventory.services.UnitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/transaction")
@RestController
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Autowired
    private StockService stockService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UnitService unitService;

    @PostMapping
    public ResponseMessage<TransactionResponse> add(@RequestBody @Valid TransactionRequest model, StockRequest stockModel) {
        Transaction entity = modelMapper.map(model, Transaction.class);

        Item item = itemService.findById(model.getItemId());
        entity.setTotalPrice(item.getPrice().longValue() * model.getQuantity());
        entity.setItem(item);
        entity.setDate(LocalDateTime.now());

        entity = service.save(entity);

        //stock
        Stock stock = modelMapper.map(model, Stock.class);

        stock.setQuantity(0 - model.getQuantity());
        item = itemService.findById(model.getItemId());
        stock.setTotalPrice(0 - item.getPrice() * model.getQuantity());
        stock.setItem(item);

        stockService.save(stock);

        TransactionResponse data = modelMapper.map(entity, TransactionResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<TransactionElement>> findAll(
            @Valid TransactionSearch model
    ) {
        Transaction search = modelMapper.map(model, Transaction.class);

        Page<Transaction> entityPage = service.findAll(search, model.getPage(), model.getSize(), model.getSort());
        List<Transaction> entities = entityPage.toList();

        List<TransactionElement> models = entities.stream()
                .map(e -> modelMapper.map(e, TransactionElement.class))
                .collect(Collectors.toList());

        PagedList<TransactionElement> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());
        return ResponseMessage.success(data);
    }
}
