package com.enigma.api.inventory.repository;

import com.enigma.api.inventory.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
