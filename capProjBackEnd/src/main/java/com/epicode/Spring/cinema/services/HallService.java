package com.epicode.Spring.cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.Spring.cinema.entities.Hall;
import com.epicode.Spring.cinema.repositories.HallRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HallService {
	
	@Autowired private HallRepository hallRepository;
	
	public Hall create(Hall h) {
		return hallRepository.save(h);
	}
	
	public List<Hall> getAll() {
		return (List<Hall>) hallRepository.findAll();
	}
	
	public List<Hall> getAllAvailableHalls() {
		return (List<Hall>) hallRepository.findAvailableHalls();
	}
	
	public Hall get(long id) {
		if(!hallRepository.existsById(id)) throw new EntityNotFoundException("Couldn't find this hall.");
		return hallRepository.findById(id).get();
	}
	
	public Hall edit(long id, Hall h) {
		if(!hallRepository.existsById(id) || id!=h.getId()) throw new EntityNotFoundException("Couldn't find this hall.");
		return hallRepository.save(h);
	}
}
