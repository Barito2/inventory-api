package com.enigma.api.inventory.services.impl;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.repository.ItemRepository;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ItemServiceImpl extends CommonServiceImpl<Item, Integer> implements ItemService {
    @Autowired
    private UnitService unitService;

    @Autowired
    public ItemServiceImpl(ItemRepository repository) {
        super(repository);
    }

//    @Transactional
//    @Override
//    public Item addItemWithUnit(Item entity) {
//        Unit unit = unitService.save(entity.getUnit());
//        entity.setUnit(unit);
//
//        return repository.save(entity);
//    }
}
