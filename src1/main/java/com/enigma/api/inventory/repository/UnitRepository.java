package com.enigma.api.inventory.repository;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Integer> {

}
