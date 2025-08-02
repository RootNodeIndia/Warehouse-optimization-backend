package com.example.WarehouseOptimizationBackend.controller;


import com.example.WarehouseOptimizationBackend.model.Bin;
import com.example.WarehouseOptimizationBackend.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/bins")
@Tag(name = "Bin Controller", description = "Manage warehouse bins")
public class BinController {

    @Autowired
    private BinService binService;

    @GetMapping
    @Operation(summary = "Get all bins", description = "Fetches all bins from the warehouse")
    public List<Bin> getAllBins() {
        return binService.getAllBins();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get by ID", description = "Fetch a single bin using ID")
    public Bin getBinById(@Parameter(description = "Bin ID") @PathVariable String id) {
        return binService.getBinById(id).orElse(null);
    }

    @PostMapping
    @Operation(summary = "Add a new Bin", description = "Creates and stores a new bin")
    public Bin addBin(@RequestBody Bin bin) {
        return binService.addBin(bin);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a bin", description = "Update an existing bin by ID")
    public Bin updateBin(@Parameter(description = "Bin ID") @PathVariable String id, @RequestBody Bin bin) {
        return binService.updateBin(id, bin);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a bin", description = "Deletes a bin by its ID")
    public void deleteBin(@Parameter(description = "Bin ID") @PathVariable String id) {
        binService.deleteBin(id);
    }

    @PostMapping("/add")
    @Operation(summary = "Add multiple bins", description = "Create and store multiple bins at once")
    public List<Bin> addMultipleBins(@RequestBody List<Bin> bins) {
        return binService.addMultipleBins(bins);
    }
}
