package com.example.WarehouseOptimizationBackend.repository;
import com.example.WarehouseOptimizationBackend.model.Bin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BinRepository extends MongoRepository<Bin, String> {
}