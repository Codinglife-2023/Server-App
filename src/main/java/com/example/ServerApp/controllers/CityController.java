package com.example.ServerApp.controllers;

import com.example.ServerApp.entities.City;
import com.example.ServerApp.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public City addCity(@RequestBody City city) {
        return cityRepository.save(city);
    }
}
