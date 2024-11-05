package com.example.ServerApp.repositories;

import com.example.ServerApp.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
