package com.example.ServerApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository<City> extends JpaRepository<City, Long> {
}
