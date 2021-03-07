package com.enigma.api.inventory.repository;

import com.enigma.api.inventory.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
