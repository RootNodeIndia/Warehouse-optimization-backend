package com.example.WarehouseOptimizationBackend.controller;


import com.example.WarehouseOptimizationBackend.model.Bin;
import com.example.WarehouseOptimizationBackend.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bins")
public class BinController {

    @Autowired
    private BinService binService;

    @GetMapping
    public List<Bin> getAllBins() {
        return binService.getAllBins();
    }

    @GetMapping("/{id}")
    public Bin getBinById(@PathVariable String id) {
        return binService.getBinById(id).orElse(null);
    }

    @PostMapping
    public Bin addBin(@RequestBody Bin bin) {
        return binService.addBin(bin);
    }

    @PutMapping("/{id}")
    public Bin updateBin(@PathVariable String id, @RequestBody Bin bin) {
        return binService.updateBin(id, bin);
    }

    @DeleteMapping("/{id}")
    public void deleteBin(@PathVariable String id) {
        binService.deleteBin(id);
    }
}
