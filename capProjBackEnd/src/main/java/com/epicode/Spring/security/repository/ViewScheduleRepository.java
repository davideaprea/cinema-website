package com.epicode.Spring.security.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.epicode.Spring.security.entity.Hall;
import com.epicode.Spring.security.entity.Movie;
import com.epicode.Spring.security.entity.ViewSchedule;

public interface ViewScheduleRepository extends CrudRepository<ViewSchedule, Long>{
	
	@Query("SELECT v FROM ViewSchedule v WHERE v.hall=:hall AND (:startTime BETWEEN v.startTime AND v.endTime OR :endTime BETWEEN v.startTime AND v.endTime")
	public ViewSchedule checkHallAvailability(Hall hall, LocalDateTime startTime, LocalDate endTime);
	
	@Query("SELECT v FROM ViewSchedule v WHERE v.endTime>:startTime AND v.film=:film")
	public ViewSchedule checkMovieAvailability(LocalDateTime startTime, Movie movie);
}
