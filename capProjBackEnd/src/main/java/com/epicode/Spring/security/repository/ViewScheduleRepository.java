package com.epicode.Spring.security.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.epicode.Spring.security.entity.Hall;
import com.epicode.Spring.security.entity.Movie;
import com.epicode.Spring.security.entity.ViewSchedule;

public interface ViewScheduleRepository extends CrudRepository<ViewSchedule, Long>{
	
//	@Query("SELECT v FROM ViewSchedule v WHERE v.hall=:hall AND (:startTime BETWEEN v.startTime AND v.endTime OR :endTime BETWEEN v.startTime AND v.endTime)")
//	public ViewSchedule checkHallAvailability(Hall hall, LocalDateTime startTime, LocalDateTime endTime);
//	
//	@Query("SELECT v FROM ViewSchedule v WHERE v.movie=:movie AND (:startTime BETWEEN v.startTime AND v.endTime OR :endTime BETWEEN v.startTime AND v.endTime)")
//	public ViewSchedule checkMovieAvailability(Movie movie, LocalDateTime startTime, LocalDateTime endTime);
	
	@Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM ViewSchedule v WHERE v.hall = :hall AND (:startTime BETWEEN v.startTime AND v.endTime OR :endTime BETWEEN v.startTime AND v.endTime)")
	public boolean checkHallAvailability(Hall hall, LocalDateTime startTime, LocalDateTime endTime);

	@Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM ViewSchedule v WHERE v.movie = :movie AND (:startTime BETWEEN v.startTime AND v.endTime OR :endTime BETWEEN v.startTime AND v.endTime)")
	public boolean checkMovieAvailability(Movie movie, LocalDateTime startTime, LocalDateTime endTime);
}
