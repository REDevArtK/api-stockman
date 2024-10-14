package com.api.stockman.entities;

import java.time.LocalDateTime;
import java.util.Map;

import com.api.stockman.utilities.JsonbConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stocks")
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private int quantity;
    private String serialNumber;
    @Convert(converter = JsonbConverter.class)//custom converted needed
    private Map<String, Object> additionalInfo;
    private String imagePath;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}

