package com.example.ServerApp.controllers;

import com.example.ServerApp.entities.Airport;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private List<Airport> airports = new ArrayList<>();

    @GetMapping
    public List<Airport> getAllAirports() {
        return airports;
    }

    @PostMapping
    public String addAirport(@RequestBody Airport airport) {
        airports.add(airport);
        return airport.getName() + " added.";
    }

    @GetMapping("/{id}")
    public Airport getAirportById(@PathVariable Long id) {
        return airports.stream()
                .filter(airport -> airport.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public String updateAirport(@PathVariable Long id, @RequestBody Airport updatedAirport) {
        for (Airport airport : airports) {
            if (airport.getId().equals(id)) {
                airport.setName(updatedAirport.getName());
                airport.setCode(updatedAirport.getCode());
                return airport.getName() + " updated.";
            }
        }
        return "Airport not found.";
    }

    @DeleteMapping("/{id}")
    public String deleteAirport(@PathVariable Long id) {
        airports.removeIf(airport -> airport.getId().equals(id));
        return "Airport deleted.";
    }
}
