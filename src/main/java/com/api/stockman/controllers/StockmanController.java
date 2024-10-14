package com.api.stockman.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.api.stockman.dtos.CreateStockDto;
import com.api.stockman.dtos.UpdateStockDto;
import com.api.stockman.entities.Stock;
import com.api.stockman.services.StockService;
import com.api.stockman.utilities.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * StockmanController
 */
@Controller
@Slf4j
@AllArgsConstructor // Generates an all-arguments constructor
@RequestMapping("api/stock")
public class StockmanController {

    private final StockService stockService; // Service for handling stock logic

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createStock(@RequestBody CreateStockDto createStockDTO) {
        try {
            Stock createdStock = stockService.createStock(createStockDTO);
            log.info("Created new stock: {}", createdStock);
            return ApiResponse.success(new HashMap<>(), "Create Success");
        } catch (Exception e) {
            log.error("Error occurred while creating stock: "+e.getMessage());
            return ApiResponse.failed(new HashMap<>(), e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> listStocks() {
        try {
            List<Stock> stocks = stockService.listStocks();
            log.info("Listing stocks: {}", stocks);
            return ApiResponse.success(stocks, "Getting List Stock Success");
        } catch (Exception e) {
            log.error("Error occurred while listing stocks: "+e.getMessage());
            return ApiResponse.failed(null, e.getMessage());
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Map<String, Object>> getStockDetail(@PathVariable Long id) {
        try {
            Optional<Stock> stocks = stockService.getStockDetail(id);
            log.info("Detail stocks: {}", stocks);
            return ApiResponse.success(stocks, "Getting Detail Stock Success");
        } catch (Exception e) {
            log.error("Error occurred while get detail stocks: "+e.getMessage());
            return ApiResponse.failed(null, e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateStock(@PathVariable Long id, @RequestBody UpdateStockDto updateStockDTO) {
        try {
            Stock updatedStock = stockService.updateStock(id, updateStockDTO);
            log.info("Updated stock: {}", updatedStock);
            return ApiResponse.success(new HashMap<>(), "Update Success");
        } catch (Exception e) {
            log.error("Error occurred while updating stock: "+e.getMessage());
            return ApiResponse.failed(new HashMap<>(), e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteStock(@PathVariable Long id) {
        try {
            stockService.deleteStock(id);
            log.info("Deleted stock with id: {}", id);
            return ApiResponse.success(new HashMap<>(), "Delete Success");
        } catch (Exception e) {
            log.error("Error occurred while deleting stock: "+e.getMessage());
            return ApiResponse.failed(new HashMap<>(), e.getMessage());
        }
    }
}