package com.example.WarehouseOptimizationBackend.model;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productId;
    private String productName;
    private Date expiryDate;
    private Date manufacturingDate;
    private int quantityAvailable;
}