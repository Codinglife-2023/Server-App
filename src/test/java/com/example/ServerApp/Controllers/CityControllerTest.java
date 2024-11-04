package com.example.ServerApp.Controllers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.ServerApp.controllers.CityController;
import com.example.ServerApp.models.City;

class CityControllerTest {

    private MockMvc mockMvc;
    private CityController cityController;

    @BeforeEach
    void setUp() {
        cityController = new CityController();
        mockMvc = MockMvcBuilders.standaloneSetup(cityController).build();
    }

    @Test
    void testCityController() throws Exception {
        mockMvc.perform(get("/api/cities"))
               .andExpect(status().isOk());
    }
    
    @Test
    void testAddCity() {
        City newCity = new City();
        newCity.setId(2L);
        newCity.setName("Los Angeles");
        newCity.setState("CA");
        newCity.setPopulation(4000000);

        String response = cityController.addCity(newCity);

        assertEquals("Los Angeles added.", response);
    }

    @Test
    void testGetCityById() {
        City city = new City();
        city.setId(1L);
        city.setName("New York");
        city.setState("NY");
        city.setPopulation(8000000);
        cityController.addCity(city);

        City result = cityController.getCityById(1L);

        assertEquals("New York", result.getName());
    }

    @Test
    void testUpdateCity() {
        City city = new City();
        city.setId(1L);
        city.setName("New York");
        city.setState("NY");
        city.setPopulation(8000000);
        cityController.addCity(city);

        City updatedCity = new City();
        updatedCity.setName("New York Updated");
        updatedCity.setState("NY");
        updatedCity.setPopulation(8500000);

        String response = cityController.updateCity(1L, updatedCity);

        assertEquals("New York Updated updated.", response);
    }

    @Test
    void testDeleteCity() {
        City city = new City();
        city.setId(1L);
        city.setName("New York");
        city.setState("NY");
        city.setPopulation(8000000);
        cityController.addCity(city);

        String response = cityController.deleteCity(1L);

        assertEquals("City deleted.", response);
    }
}