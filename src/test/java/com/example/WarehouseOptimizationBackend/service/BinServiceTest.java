package com.example.WarehouseOptimizationBackend.service;


import com.example.WarehouseOptimizationBackend.model.Bin;
import com.example.WarehouseOptimizationBackend.model.Product;
import com.example.WarehouseOptimizationBackend.repository.BinRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BinServiceTest {

    @Mock
    private BinRepository binRepository;

    @InjectMocks
    private BinService binService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldReturnAllBins() {
        List<Bin> bins = List.of(
                new Bin("1", "Bin A", "Zone 1", "A1", "R1", "S1", 100, new ArrayList<>()),
                new Bin("2", "Bin B", "Zone 2", "B2", "R2", "S2", 200, new ArrayList<>())
        );

        when(binRepository.findAll()).thenReturn(bins);

        List<Bin> result = binService.getAllBins();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getBinName()).isEqualTo("Bin A");
        verify(binRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnBinById() {
        Bin bin = new Bin("1", "Bin A", "Zone 1", "A1", "R1", "S1", 100, new ArrayList<>());

        when(binRepository.findById("1")).thenReturn(Optional.of(bin));

        Optional<Bin> result = binService.getBinById("1");

        assertThat(result).isPresent();
        assertThat(result.get().getBinName()).isEqualTo("Bin A");
        verify(binRepository).findById("1");
    }

    @Test
    void shouldAddNewBin() {
        Bin bin = new Bin("3", "Bin C", "Zone 3", "C3", "R3", "S3", 300, new ArrayList<>());

        when(binRepository.save(bin)).thenReturn(bin);

        Bin result = binService.addBin(bin);

        assertThat(result.getBinName()).isEqualTo("Bin C");
        verify(binRepository).save(bin);
    }

    @Test
    void shouldUpdateBin() {
        Bin existingBin = new Bin("4", "Old Bin", "Zone 4", "D1", "R1", "S2", 400, new ArrayList<>());
        Bin updatedBin = new Bin("4", "Updated Bin", "Zone 4", "D1", "R1", "S2", 450, new ArrayList<>());

        when(binRepository.findById("4")).thenReturn(Optional.of(existingBin));
        when(binRepository.save(updatedBin)).thenReturn(updatedBin);

        Bin result = binService.updateBin("4", updatedBin);

        assertThat(result.getBinName()).isEqualTo("Updated Bin");
        verify(binRepository).save(updatedBin);
    }


    @Test
    void shouldDeleteBin() {
        binService.deleteBin("5");

        verify(binRepository).deleteById("5");
    }

    @Test
    void shouldAddMultipleBins() {
        List<Bin> bins = List.of(
                new Bin("6", "Bin E", "Zone 5", "E1", "R1", "S1", 500, new ArrayList<>()),
                new Bin("7", "Bin F", "Zone 5", "E2", "R2", "S2", 600, new ArrayList<>())
        );

        when(binRepository.saveAll(bins)).thenReturn(bins);

        List<Bin> result = binService.addMultipleBins(bins);

        assertThat(result).hasSize(2);
        verify(binRepository).saveAll(bins);
    }

}
