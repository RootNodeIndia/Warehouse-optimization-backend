package com.example.WarehouseOptimizationBackend.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bins")
public class Bin {
    @Id
    private String binId;
    private String binName;
    private String zone;
    private String rowAisle;
    private String bay;
    private String level;
    private int distanceRank;
    private List<Product> productsStored;
}