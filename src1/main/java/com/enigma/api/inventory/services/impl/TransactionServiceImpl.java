package com.enigma.api.inventory.services.impl;

import com.enigma.api.inventory.entities.Transaction;
import com.enigma.api.inventory.repository.TransactionRepository;
import com.enigma.api.inventory.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl extends CommonServiceImpl<Transaction, Integer> implements TransactionService {

    @Autowired
    public TransactionServiceImpl(TransactionRepository repository) {
        super(repository);
    }
}
