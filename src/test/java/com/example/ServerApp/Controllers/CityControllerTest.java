package com.example.ServerApp.controllers;

import com.example.ServerApp.entities.City;
import com.example.ServerApp.repositories.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CityController.class)
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityRepository<City> cityRepository;

    @InjectMocks
    private CityController cityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cityController).build();
    }

    @Test
    void testGetAllCities() throws Exception {
        City city = new City("New York", "NY", 8419600);
        when(cityRepository.findAll()).thenReturn(Collections.singletonList(city));

        mockMvc.perform(get("/api/cities"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'name':'New York','state':'NY','population':8419600}]"));
    }

    @Test
    void testAddCity() throws Exception {
        City city = new City("Los Angeles", "CA", 3980400);
        when(cityRepository.save(city)).thenReturn(city);

        mockMvc.perform(post("/api/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Los Angeles\", \"state\":\"CA\", \"population\":3980400}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Los Angeles added."));
    }
}
