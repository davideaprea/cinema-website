package com.epicode.Spring.security.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.epicode.Spring.security.entity.Hall;
import com.epicode.Spring.security.entity.Movie;
import com.epicode.Spring.security.entity.ViewSchedule;

public interface ViewScheduleRepository extends CrudRepository<ViewSchedule, Long>{
	
	@Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM ViewSchedule v WHERE v.hall = :hall AND (:startTime BETWEEN v.startTime AND v.endTime OR :endTime BETWEEN v.startTime AND v.endTime)")
	public boolean checkHallAvailability(Hall hall, LocalDateTime startTime, LocalDateTime endTime);

	@Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM ViewSchedule v WHERE v.movie = :movie AND (:startTime BETWEEN v.startTime AND v.endTime OR :endTime BETWEEN v.startTime AND v.endTime)")
	public boolean checkMovieAvailability(Movie movie, LocalDateTime startTime, LocalDateTime endTime);
	
	@Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM ViewSchedule v WHERE NOT (v.id = :id) AND v.hall = :hall AND (:startTime BETWEEN v.startTime AND v.endTime OR :endTime BETWEEN v.startTime AND v.endTime)")
	public boolean checkHallAvailability(long id, Hall hall, LocalDateTime startTime, LocalDateTime endTime);

	@Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM ViewSchedule v WHERE NOT (v.id = :id) AND v.movie = :movie AND (:startTime BETWEEN v.startTime AND v.endTime OR :endTime BETWEEN v.startTime AND v.endTime)")
	public boolean checkMovieAvailability(long id, Movie movie, LocalDateTime startTime, LocalDateTime endTime);
	
	@Query("SELECT DISTINCT v.movie FROM ViewSchedule v WHERE v.startTime > CURRENT_TIMESTAMP")
	public List<Movie> scheduledMovies();
	
	@Query("SELECT v FROM ViewSchedule v WHERE v.startTime > CURRENT_TIMESTAMP AND v.movie=:movie ORDER BY v.startTime ASC")
	public List<ViewSchedule> findMovieSchedules(Movie movie);
	
	@Query("SELECT v FROM ViewSchedule v WHERE v.startTime > CURRENT_TIMESTAMP ORDER BY v.startTime ASC")
	public List<ViewSchedule> findNextSchedules();
	
	@Query("SELECT v FROM ViewSchedule v WHERE v.startTime > CURRENT_TIMESTAMP AND v.endTime < CURRENT_TIMESTAMP ORDER BY v.startTime ASC")
	public List<ViewSchedule> findCurrentSchedules();
}
