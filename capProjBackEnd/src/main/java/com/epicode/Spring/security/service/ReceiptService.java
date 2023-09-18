package com.epicode.Spring.security.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CancellationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.Spring.security.entity.Booking;
import com.epicode.Spring.security.entity.Receipt;
import com.epicode.Spring.security.entity.User;
import com.epicode.Spring.security.entity.ViewSchedule;
import com.epicode.Spring.security.payload.BookingDto;
import com.epicode.Spring.security.payload.BookingResponse;
import com.epicode.Spring.security.payload.ReceiptDto;
import com.epicode.Spring.security.payload.ReceiptResponse;
import com.epicode.Spring.security.payload.ViewScheduleResponse;
import com.epicode.Spring.security.repository.BookingRepository;
import com.epicode.Spring.security.repository.ReceiptRepository;
import com.epicode.Spring.security.repository.UserRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ReceiptService {
	@Autowired private ReceiptRepository receiptRepo;
	@Autowired private BookingRepository bookingRepo;
	@Autowired private ViewScheduleService viewScheduleService;
	@Autowired private UserRepository userRepo;
	
	public ReceiptResponse create(ReceiptDto r) {
		long vsId=r.getBookings().iterator().next().getScheduleId();
		ViewSchedule vs=viewScheduleService.getOriginalSchedule(vsId);
		
		Set<Booking> bookings=new HashSet<Booking>();
		for(BookingDto b : r.getBookings()) {
			Booking booking=new Booking();
			booking.setViewSchedule(vs);
			booking.setSeat(b.getSeat());
			bookings.add(booking);
		}
		
		User user=new User();
		String usernameOrEmail=r.getUser().getUsername();
        if(userRepo.existsByUsername(usernameOrEmail))
        	user=userRepo.findByUsername(usernameOrEmail).get();
        else user=userRepo.findByEmail(usernameOrEmail).get();
		
		Receipt receipt=new Receipt();
		receipt.setBookings(bookings);
		receipt.setUser(user);
		
		for(Booking b : receipt.getBookings()) {
			if(bookingRepo.existsByViewScheduleAndSeat(b.getViewSchedule(), b.getSeat()))
				throw new EntityExistsException("This seat is already booked.");
		}
		
		Receipt receiptSaved=receiptRepo.save(receipt);
		
		return new ReceiptResponse(
				receiptSaved.getId(),
				receiptSaved.getUser(),
				getBookingResponses(receiptSaved),
				receiptSaved.getPurchaseTime(),
				receiptSaved.getTotPrice());
	}
	
	public ReceiptResponse get(long id) {
		if(!receiptRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this Receipt.");
		Receipt receipt=receiptRepo.findById(id).get();
		
		return new ReceiptResponse(
				receipt.getId(),
				receipt.getUser(),
				getBookingResponses(receipt),
				receipt.getPurchaseTime(),
				receipt.getTotPrice());
	}
	
	public Receipt edit(long id, Receipt r) {
		if(!receiptRepo.existsById(id) || id!=r.getId()) throw new EntityNotFoundException("Couldn't find this Receipt.");
		return receiptRepo.save(r);
	}
	
	public String remove(long id) {
		if(!receiptRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this Receipt.");
		
		checkDeleteTimeLimit(id);
		
		receiptRepo.deleteById(id);
		return "Receipt deleted successfully.";
	}
	
	private Set<BookingResponse> getBookingResponses(Receipt receipt){
		long vsId=receipt.getBookings().iterator().next().getViewSchedule().getId();
		Set<BookingResponse> bRes=new HashSet<BookingResponse>();
		ViewScheduleResponse vsRes=viewScheduleService.get(vsId);
		for(Booking b : receipt.getBookings()) bRes.add(new BookingResponse(b.getId(), vsRes, b.getSeat(), b.getReceipt()));
		return bRes;
	}
	
	private void checkDeleteTimeLimit(long id) {
		LocalDateTime scheduleStartTime=bookingRepo.findById(id).get().getViewSchedule().getStartTime();
		if(Duration.between(LocalDateTime.now(), scheduleStartTime).toHours()<=3)
			throw new CancellationException("You can cancel your booking only within 3 hours of the schedule start time.");
	}
}
