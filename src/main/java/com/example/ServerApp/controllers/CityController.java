package com.example.ServerApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.ServerApp.repositories.CityRepository;

import com.example.ServerApp.entities.City;

@RestController
@RequestMapping("/api/cities")

public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
    
    @PostMapping
    public String addCity(@RequestBody City city) {
        if (city.getName() == null) {
            return "City name is required.";
        }
        cityRepository.save(city);
        return city.getName() + " added.";
    }
     
    
    public City getCityById(long l) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCityById'");
    }

    public String updateCity(long l, City updatedCity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCity'");
    }

    public String deleteCity(long l) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCity'");
    }
}
