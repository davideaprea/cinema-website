package com.epicode.Spring.security.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.Spring.security.payload.BookingResponse;
import com.epicode.Spring.security.service.BookingService;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookingController {
	@Autowired private BookingService bookingService;
	
	@GetMapping("/schedule-bookings/{id}")
	public ResponseEntity<?> getById(@PathVariable long id) {
		return new ResponseEntity<Set<BookingResponse>>(bookingService.getBySchedule(id), HttpStatus.OK);
    }
}
