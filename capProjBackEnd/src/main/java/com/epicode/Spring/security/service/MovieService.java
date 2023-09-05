package com.epicode.Spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.Spring.security.entity.Movie;
import com.epicode.Spring.security.repository.MovieRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MovieService {
	@Autowired private MovieRepository movieRepo;
	
	public Movie create(Movie m) {
		if(movieRepo.existsByTitleAndIsTridimensional(m.getTitle(), m.getIsTridimensional()))
			throw new EntityExistsException("This movie already exists.");
		return movieRepo.save(m);
	}
	
	public Movie get(long id) {
		if(!movieRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this movie.");
		return movieRepo.findById(id).get();
	}
	
	public Movie edit(long id, Movie m) {
		if(!movieRepo.existsById(id) || id!=m.getId()) throw new EntityNotFoundException("Couldn't find this movie.");
		return movieRepo.save(m);
	}
	
	public String remove(long id) {
		if(!movieRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this movie.");
		movieRepo.deleteById(id);
		return "Movie deleted successfully.";
	}
}
