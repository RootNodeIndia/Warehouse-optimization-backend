package com.example.WarehouseOptimizationBackend.controller;

import com.example.WarehouseOptimizationBackend.model.Bin;
import com.example.WarehouseOptimizationBackend.service.BinService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BinController.class)
public class BinControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BinService binService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllBins() throws Exception {
        List<Bin> bins = List.of(
                new Bin("1", "Bin Test", "Zone X", "A1", "R1", "S1", 100, new ArrayList<>())
        );

        when(binService.getAllBins()).thenReturn(bins);

        mockMvc.perform(get("/api/bins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].binName").value("Bin Test"));
    }

    @Test
    void shouldGetBinById() throws Exception {
        Bin bin = new Bin("1", "Bin A", "Zone X", "A1", "R1", "S1", 100, new ArrayList<>());
        when(binService.getBinById("1")).thenReturn(Optional.of(bin));

        mockMvc.perform(get("/api/bins/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.binName").value("Bin A"));
    }

    @Test
    void shouldCreateNewBin() throws Exception {
        Bin bin = new Bin("2", "New Bin", "Zone Y", "B1", "R2", "S2", 150, new ArrayList<>());
        when(binService.addBin(any(Bin.class))).thenReturn(bin);

        mockMvc.perform(post("/api/bins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.binName").value("New Bin"));
    }

    @Test
    void shouldAddMultipleBins() throws Exception {
        List<Bin> bins = List.of(
                new Bin("3", "Bin A", "Zone Z", "C1", "R3", "S3", 200, new ArrayList<>()),
                new Bin("4", "Bin B", "Zone Z", "C2", "R4", "S4", 300, new ArrayList<>())
        );

        when(binService.addMultipleBins(anyList())).thenReturn(bins);

        mockMvc.perform(post("/api/bins/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bins)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].binName").value("Bin A"))
                .andExpect(jsonPath("$[1].binName").value("Bin B"));
    }

    @Test
    void shouldUpdateBin() throws Exception {
        Bin updatedBin = new Bin("1", "Updated Bin", "Zone X", "A1", "R1", "S1", 120, new ArrayList<>());
        when(binService.updateBin(eq("1"), any(Bin.class))).thenReturn(updatedBin);

        mockMvc.perform(put("/api/bins/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.binName").value("Updated Bin"));
    }

    @Test
    void shouldDeleteBin() throws Exception {
        doNothing().when(binService).deleteBin("1");

        mockMvc.perform(delete("/api/bins/1"))
                .andExpect(status().isOk());
    }
}
