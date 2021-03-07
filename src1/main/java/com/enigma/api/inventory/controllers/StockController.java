package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.entities.StockSummary;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.messages.ResponseMessage;
import com.enigma.api.inventory.models.PagedList;
import com.enigma.api.inventory.models.stocks.StockElement;
import com.enigma.api.inventory.models.stocks.StockRequest;
import com.enigma.api.inventory.models.stocks.StockResponse;
import com.enigma.api.inventory.models.stocks.StockSearch;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.StockService;
import com.enigma.api.inventory.services.UnitService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/stocks")
@RestController
public class StockController {

    @Autowired
    private StockService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UnitService unitService;

    @GetMapping("/{id}")
    public ResponseMessage findById(@PathVariable Integer id) {

        Stock entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        StockResponse data = modelMapper.map(entity, StockResponse.class);

        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<StockElement>> findAll(
            @Valid StockSearch model
    ) {
        Stock search = modelMapper.map(model, Stock.class);

        Page<Stock> entityPage = service.findAll(search, model.getPage(), model.getSize(), model.getSort());
        List<Stock> entities = entityPage.toList();

        List<StockElement> models = entities.stream()
                .map(e -> modelMapper.map(e, StockElement.class))
                .collect(Collectors.toList());

        PagedList<StockElement> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());
        return ResponseMessage.success(data);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server error",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseMessage.class))))
    })
    @PostMapping
    public ResponseMessage<StockResponse> add(@RequestBody @Valid StockRequest model) {
        Stock entity = modelMapper.map(model, Stock.class);

        Item item = itemService.findById(model.getItemId());
        entity.setTotalPrice(model.getQuantity() * item.getPrice());
        entity.setItem(item);

        entity = service.save(entity);

        StockResponse data = modelMapper.map(entity, StockResponse.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<StockResponse> edit(@PathVariable @Valid Integer id, @RequestBody StockRequest model) {

        Stock entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        Item item = itemService.findById(model.getItemId());
        entity.setTotalPrice(model.getQuantity() * item.getPrice());
        entity.setItem(item);

        modelMapper.map(model, entity);
        entity = service.save(entity);

        StockResponse data = modelMapper.map(entity, StockResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage removeById(@PathVariable Integer id) {
        Stock entity = service.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        StockResponse data = modelMapper.map(entity, StockResponse.class);
        return ResponseMessage.success(data);
    }

        @GetMapping("/summaries")
    public ResponseMessage<List<StockSummary>> findAllSummary() {
        List<StockSummary> entityPage = service.findAllSummaries();
        return ResponseMessage.success(entityPage);
    }
}
