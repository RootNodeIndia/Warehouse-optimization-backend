package com.example.WarehouseOptimizationBackend.service;

import com.example.WarehouseOptimizationBackend.model.Bin;
import com.example.WarehouseOptimizationBackend.repository.BinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BinService {

    @Autowired
    private BinRepository binRepository;

    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    public Optional<Bin> getBinById(String id) {
        return binRepository.findById(id);
    }

    public Bin addBin(Bin bin) {
        return binRepository.save(bin);
    }

    public Bin updateBin(String id, Bin bin) {
        bin.setBinId(id);
        return binRepository.save(bin);
    }

    public void deleteBin(String id) {
        binRepository.deleteById(id);
    }
}

