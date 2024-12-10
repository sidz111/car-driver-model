package com.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.card.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
	@Query("SELECT c FROM Car c WHERE c.driver.name = :driverName")
	Car findCarByDriverName(@Param("driverName") String driverName);
}
