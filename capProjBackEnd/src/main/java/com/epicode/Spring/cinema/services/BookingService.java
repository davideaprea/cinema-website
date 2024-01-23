package com.epicode.Spring.cinema.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.Spring.cinema.entities.Booking;
import com.epicode.Spring.cinema.entities.ViewSchedule;
import com.epicode.Spring.cinema.repositories.BookingRepository;

@Service
public class BookingService {
	@Autowired private BookingRepository bookingRepository;
	@Autowired private ViewScheduleService viewScheduleService;
	
	public List<Booking> getBySchedule(long id){
		ViewSchedule viewSchedule = viewScheduleService.get(id);
		return bookingRepository.findByViewSchedule(viewSchedule);
	}
	
	public Map<Double, ViewSchedule> getSchedulesBookingPercentage(){
		return bookingRepository.getSchedulesBookingPercentage();
	}
}
