package com.epicode.Spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.Spring.security.entity.Hall;
import com.epicode.Spring.security.repository.HallRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HallService {
	
	@Autowired private HallRepository hallRepository;
	
	public Hall create(Hall h) {
		return hallRepository.save(h);
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
