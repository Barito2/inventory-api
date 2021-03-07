package com.enigma.api.inventory.repository;

import com.enigma.api.inventory.entities.StockSummary;
import net.bytebuddy.build.Plugin;

import java.util.List;

public interface StockSummaryRepository {
    public List<StockSummary> findAllSummaries();
}
