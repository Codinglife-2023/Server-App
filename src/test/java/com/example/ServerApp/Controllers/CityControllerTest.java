package com.example.ServerApp.controllers;

import com.example.ServerApp.entities.City;
import com.example.ServerApp.repositories.CityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityRepository cityRepository;

    @Test
    public void testGetAllCities() throws Exception {
        Mockito.when(cityRepository.findAll()).thenReturn(List.of(
                new City("Test City 1", "TS1", 1000),
                new City("Test City 2", "TS2", 2000)
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test City 1"))
                .andExpect(jsonPath("$[0].state").value("TS1"))
                .andExpect(jsonPath("$[0].population").value(1000))
                .andExpect(jsonPath("$[1].name").value("Test City 2"))
                .andExpect(jsonPath("$[1].state").value("TS2"))
                .andExpect(jsonPath("$[1].population").value(2000));
    }

    @Test
    public void testAddCity() throws Exception {
        City city = new City("New City", "NC", 5000);
        Mockito.when(cityRepository.save(Mockito.any(City.class))).thenReturn(city);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New City\", \"state\":\"NC\", \"population\":5000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New City"))
                .andExpect(jsonPath("$.state").value("NC"))
                .andExpect(jsonPath("$.population").value(5000));
    }
}
