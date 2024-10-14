package com.api.stockman.dtos;

import java.util.Map;

import lombok.Data;

@Data
public class UpdateStockDto {
    private String name;
    private int quantity;
    private String serialNumber;
    private Map<String, Object> additionalInfo; // JSON string
    private String imagePath; // Path to the image
}
