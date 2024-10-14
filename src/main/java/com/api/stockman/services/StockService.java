package com.api.stockman.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.stockman.dtos.CreateStockDto;
import com.api.stockman.dtos.UpdateStockDto;
import com.api.stockman.entities.Stock;
import com.api.stockman.repositories.StockRepository;
import com.api.stockman.utilities.ImageValidation;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final ImageValidation imageValidation;

    public Stock createStock(CreateStockDto createStockDTO) {
        Stock stock = new Stock();
        stock.setName(createStockDTO.getName());
        stock.setQuantity(createStockDTO.getQuantity());
        stock.setSerialNumber(createStockDTO.getSerialNumber());
        stock.setAdditionalInfo(createStockDTO.getAdditionalInfo());

        // Validate the image path using the utility class
        if (imageValidation.isValidImagePath(createStockDTO.getImagePath())) {
            stock.setImagePath(createStockDTO.getImagePath()); // Set image path if valid
        } else {
            throw new IllegalArgumentException("Image path must be a valid PNG or JPG file.");
        }

        stock.setCreatedAt(LocalDateTime.now());
        stock.setCreatedBy("admin");
        return stockRepository.save(stock);
    }

    public List<Stock> listStocks() {
        return stockRepository.findAll(); // Fetch all Stock entities directly
    }

    public Optional<Stock> getStockDetail(Long id) {
        return stockRepository.findById(id);
    }

    public Stock updateStock(Long id, UpdateStockDto updateStockDTO) {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.setName(updateStockDTO.getName());
        stock.setQuantity(updateStockDTO.getQuantity());
        stock.setSerialNumber(updateStockDTO.getSerialNumber());
        stock.setAdditionalInfo(updateStockDTO.getAdditionalInfo());
        
        // Validate the image path using the utility class
        if (imageValidation.isValidImagePath(updateStockDTO.getImagePath())) {
            stock.setImagePath(updateStockDTO.getImagePath()); // Set image path if valid
        } else {
            throw new IllegalArgumentException("Image path must be a valid PNG or JPG file.");
        }

        stock.setUpdatedAt(LocalDateTime.now());   // Set updatedAt to current date/time
        stock.setUpdatedBy("admin");          
        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}
