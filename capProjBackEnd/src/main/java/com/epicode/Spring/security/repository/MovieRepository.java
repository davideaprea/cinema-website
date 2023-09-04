package com.epicode.Spring.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.epicode.Spring.security.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long>{
	public boolean existsByTitleAndIsTridimensional(String title, Boolean isTridimensional);
}
