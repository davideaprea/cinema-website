package com.epicode.Spring.cinema.repositories;

import org.springframework.data.repository.CrudRepository;

import com.epicode.Spring.cinema.entities.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long>{
	public boolean existsByTitleAndIsTridimensional(String title, Boolean isTridimensional);
}
