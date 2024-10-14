package com.api.stockman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.stockman.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
    //manage in services
}
