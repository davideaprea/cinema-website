package com.epicode.Spring.security.service;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.Spring.security.entity.Booking;
import com.epicode.Spring.security.entity.Receipt;
import com.epicode.Spring.security.entity.User;
import com.epicode.Spring.security.payload.ReceiptDto;
import com.epicode.Spring.security.repository.BookingRepository;
import com.epicode.Spring.security.repository.ReceiptRepository;
import com.epicode.Spring.security.statsResponses.PurchasesPerDay;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ReceiptService {
	@Autowired private ReceiptRepository receiptRepo;
	@Autowired private BookingRepository bookingRepo;
	@Autowired private AuthServiceImpl userService;
	
	public Receipt create(ReceiptDto r) {
		
		for(Booking b : r.getBookings()) {
			if(bookingRepo.existsByViewScheduleAndSeat(b.getViewSchedule(), b.getSeat()))
				throw new EntityExistsException("This seat is already booked.");
		}
		
		Receipt receipt=new Receipt();
		receipt.setBookings(r.getBookings());
		receipt.setUser(userService.getUserByEmailOrPassword(r.getUser().getUsername()));
		
		return receiptRepo.save(receipt);
	}
	
	public List<PurchasesPerDay> getLastMonthReceipts(){
		List<Object[]> data=receiptRepo.getLastMonthReceipts();
		List<PurchasesPerDay> purchasesPerDayList = new ArrayList<PurchasesPerDay>();
		
		for(Object[] stat: data)
			purchasesPerDayList.add(
					new PurchasesPerDay((Double)stat[0], (Date)stat[1])
					);
		return purchasesPerDayList;
	}
	
	public Receipt get(long id) {
		if(!receiptRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this Receipt.");
		return receiptRepo.findById(id).get();
	}
	
	public List<Receipt> getAllByUser(String credentials){
		User user=userService.getUserByEmailOrPassword(credentials);
		return receiptRepo.findByUser(user);
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
	
	private void checkDeleteTimeLimit(long id) {
		LocalDateTime scheduleStartTime=bookingRepo.findById(id).get().getViewSchedule().getStartTime();
		if(Duration.between(LocalDateTime.now(), scheduleStartTime).toHours()<=3)
			throw new CancellationException("You can cancel your booking only within 3 hours of the schedule start time.");
	}
}
