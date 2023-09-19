package com.epicode.Spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.Spring.security.entity.Booking;
import com.epicode.Spring.security.entity.ViewSchedule;
import com.epicode.Spring.security.repository.BookingRepository;

@Service
public class BookingService {
	@Autowired private BookingRepository bookingRepository;
	@Autowired private ViewScheduleService viewScheduleService;
	
	public List<Booking> getBySchedule(long id){
		ViewSchedule viewSchedule = viewScheduleService.get(id);
		return bookingRepository.findByViewSchedule(viewSchedule);
	}
}
