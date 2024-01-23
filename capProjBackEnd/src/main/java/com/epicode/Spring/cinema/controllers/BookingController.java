package com.epicode.Spring.cinema.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.Spring.cinema.entities.Booking;
import com.epicode.Spring.cinema.entities.ViewSchedule;
import com.epicode.Spring.cinema.services.BookingService;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookingController {
	@Autowired private BookingService bookingService;
	
	@GetMapping("/schedule-bookings/{id}")
	public ResponseEntity<?> getById(@PathVariable long id) {
		return new ResponseEntity<List<Booking>>(bookingService.getBySchedule(id), HttpStatus.OK);
    }
	
	@GetMapping("/schedule-bookings-perc")
	public ResponseEntity<Map<Double, ViewSchedule>> getSchedulesBookingPercentage(){
		return new ResponseEntity<Map<Double, ViewSchedule>>(bookingService.getSchedulesBookingPercentage(), HttpStatus.OK);
	}
}
