package com.example.ServerApp.controllers;
import org.springframework.web.bind.annotation.*;

import com.example.ServerApp.models.City;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private List<City> cities = new ArrayList<>();

    @GetMapping
    public List<City> getAllCities() {
        return cities;
    }

    @PostMapping
    public String addCity(@RequestBody City city) {
        cities.add(city);
        return city.getName() + " added.";
    }

    @GetMapping("/{id}")
    public City getCityById(@PathVariable Long id) {
        return cities.stream()
                .filter(city -> city.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public String updateCity(@PathVariable Long id, @RequestBody City updatedCity) {
        for (City city : cities) {
            if (city.getId().equals(id)) {
                city.setName(updatedCity.getName());
                city.setState(updatedCity.getState());
                city.setPopulation(updatedCity.getPopulation());
                return city.getName() + " updated.";
            }
        }
        return "City not found.";
    }

    @DeleteMapping("/{id}")
    public String deleteCity(@PathVariable Long id) {
        cities.removeIf(city -> city.getId().equals(id));
        return "City deleted.";
    }
}
