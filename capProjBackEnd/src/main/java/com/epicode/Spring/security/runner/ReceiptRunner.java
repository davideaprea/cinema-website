package com.epicode.Spring.security.runner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.epicode.Spring.security.entity.Booking;
import com.epicode.Spring.security.entity.Hall;
import com.epicode.Spring.security.entity.Seat;
import com.epicode.Spring.security.entity.ViewSchedule;
import com.epicode.Spring.security.payload.JWTAuthResponse;
import com.epicode.Spring.security.payload.ReceiptDto;
import com.epicode.Spring.security.service.ReceiptService;
import com.epicode.Spring.security.service.ViewScheduleService;
import com.github.javafaker.Faker;

@Component
public class ReceiptRunner implements ApplicationRunner{
	@Autowired private ViewScheduleService vss;
	@Autowired private ReceiptService rs;
	//@Autowired private Faker faker;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/* List<ViewSchedule> availableSchedules=vss.getNextSchedules();
		
		for(ViewSchedule vs : availableSchedules) {
			Hall hall=vs.getHall();
			int randomBookingsCount=(int) Math.floor(Math.random()*hall.getNRows()*hall.getNSeatsPerRow())/3;
			
			for(int i=1; i<=randomBookingsCount; i++) {
				Set<Booking> bookings=new HashSet<Booking>();
				
				for(int j=0; j<(int)Math.random()*5+1; j++) {
					Booking b=new Booking();
					int randomRow=(int) Math.random()*hall.getNRows()+1;
					int randomSeat=(int) Math.random()*hall.getNSeatsPerRow()+1;
					
					b.setViewSchedule(vs);
					b.setSeat(new Seat(randomSeat, randomRow));
					bookings.add(b);
				}
				
				ReceiptDto r=new ReceiptDto();
				r.setBookings(bookings);
				r.setUser(new JWTAuthResponse("martyapre@gmail.com", "", ""));
				
				rs.create(r);
			}
		} */
		System.out.println("Receipt runner executed.");
	}

}
