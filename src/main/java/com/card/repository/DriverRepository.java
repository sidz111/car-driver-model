package com.card.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.card.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
	@Query("SELECT d FROM Driver d WHERE d.car.id = :carId")
	Driver findDriverByCarId(@Param("carId") Integer carId);
	
	List<Driver> findByName(String name);
}
