package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.messages.ResponseMessage;
import com.enigma.api.inventory.models.ImageUploadRequest;
import com.enigma.api.inventory.models.PagedList;
import com.enigma.api.inventory.models.items.ItemElement;
import com.enigma.api.inventory.models.items.ItemRequest;
import com.enigma.api.inventory.models.items.ItemResponse;
import com.enigma.api.inventory.models.items.ItemSearch;
import com.enigma.api.inventory.services.FileService;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.UnitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/items")
@RestController
public class ItemController {

    @Autowired
    private ItemService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UnitService unitService;

    @Autowired
    private FileService fileService;

    @GetMapping("/{id}")
    public ResponseMessage findById(@PathVariable Integer id) {

        Item entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        ItemResponse data = modelMapper.map(entity, ItemResponse.class);

        return ResponseMessage.success(data);
    }

    @GetMapping()
    public ResponseMessage<PagedList<ItemElement>> findAll(@Valid ItemSearch model) {
        Item search = modelMapper.map(model, Item.class);

        Page<Item> entityPage = service.findAll(search, model.getPage(), model.getSize(), model.getSort());
        List<Item> entities = entityPage.toList();

        List<ItemElement> models = entities.stream()
                .map(e -> modelMapper.map(e, ItemElement.class))
                .collect(Collectors.toList());

        PagedList<ItemElement> data = new PagedList(models,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }



//    @PostMapping("/with-unit")
//    public ResponseMessage<ItemResponse> addWithUnit(@RequestBody @Valid ItemResponse model) {
//        Item entity = modelMapper.map(model, Item.class);
//
//        entity = service.addItemWithUnit(entity);
//
//        ItemResponse data = modelMapper.map(entity, ItemResponse.class);
//        return ResponseMessage.success(data);
//    }

    @PostMapping
    public ResponseMessage<ItemResponse> add(@RequestBody @Valid ItemRequest model) {
        Item entity = modelMapper.map(model, Item.class);

        entity.setUnit(unitService.findById(model.getUnitId()));

        entity = service.save(entity);

        ItemResponse data = modelMapper.map(entity, ItemResponse.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<ItemResponse> edit(
            @PathVariable Integer id, @RequestBody @Valid ItemRequest model) {
        Item entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        Unit unit = unitService.findById(model.getUnitId());
        entity.setUnit(unit);

        modelMapper.map(model, entity);
        entity = service.save(entity);

        ItemResponse data = modelMapper.map(entity, ItemResponse.class);
        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage removeById(@PathVariable Integer id) {
        Item entity = service.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        ItemResponse data = modelMapper.map(entity, ItemResponse.class);
        return ResponseMessage.success(data);
    }

    @PostMapping(value = "/{id}/image", consumes = "multipart/form-data")
    public ResponseMessage upload(@PathVariable Integer id, @Valid ImageUploadRequest model) throws IOException {
        Item entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        fileService.upload(entity, model.getFile().getInputStream());

        return ResponseMessage.success(true);
    }

    @GetMapping("/{id}/image")
    public void download(@PathVariable Integer id, HttpServletResponse response) throws IOException {

        Item entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "filename=\""+entity.getId()+"\"");
        fileService.download(entity, response.getOutputStream());
    }
}
