package com.epicode.Spring.security.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.Spring.security.entity.Booking;
import com.epicode.Spring.security.entity.ViewSchedule;
import com.epicode.Spring.security.payload.BookingResponse;
import com.epicode.Spring.security.payload.ViewScheduleResponse;
import com.epicode.Spring.security.repository.BookingRepository;

@Service
public class BookingService {
	@Autowired private BookingRepository bookingRepository;
	@Autowired private ViewScheduleService viewScheduleService;
	
	public Set<BookingResponse> getBySchedule(long id){
		Set<BookingResponse> bRes=new HashSet<BookingResponse>();
		ViewScheduleResponse vsRes=viewScheduleService.get(id);
		ViewSchedule vsOriginal=viewScheduleService.getOriginalSchedule(id);
		List<Booking> bookings=bookingRepository.findByViewSchedule(vsOriginal);
		for(Booking b : bookings) bRes.add(new BookingResponse(b.getId(), vsRes, b.getSeat(), b.getReceipt()));
		return bRes;
	}
}
