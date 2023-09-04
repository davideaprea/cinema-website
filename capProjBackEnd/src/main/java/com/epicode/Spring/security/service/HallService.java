package com.epicode.Spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.Spring.security.entity.Hall;
import com.epicode.Spring.security.repository.HallRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HallService {
	
	@Autowired private HallRepository hs;
	
	public Hall create(Hall h) {
		return hs.save(h);
	}
	
	public Hall get(long id) {
		if(!hs.existsById(id)) throw new EntityNotFoundException("Couldn't find this hall.");
		return hs.findById(id).get();
	}
	
	public Hall edit(long id, Hall h) {
		if(!hs.existsById(id) || id!=h.getId()) throw new EntityNotFoundException("Couldn't find this hall.");
		return hs.save(h);
	}
	
	public String remove(long id) {
		if(!hs.existsById(id)) throw new EntityNotFoundException("Couldn't find this hall.");
		hs.deleteById(id);
		return "Hall deleted successfully.";
	}
}
