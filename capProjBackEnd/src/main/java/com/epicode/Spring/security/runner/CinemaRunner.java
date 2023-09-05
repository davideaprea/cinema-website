package com.epicode.Spring.security.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.epicode.Spring.security.entity.Hall;
import com.epicode.Spring.security.service.HallService;

@Component
public class CinemaRunner implements CommandLineRunner{
	@Autowired private HallService hallService;

	@Override
	public void run(String... args) throws Exception {
		
//		Hall h=new Hall();
//		h.setnRows(15);
//		h.setnSeatsPerRows(30);
//		h.setSeats();
//		
//		hallService.create(h);
		
	}
}
