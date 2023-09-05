package com.epicode.Spring.security.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CancellationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epicode.Spring.security.entity.Ticket;
import com.epicode.Spring.security.repository.TicketRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TicketService {
	@Autowired private TicketRepository ticketRepo;
	
	public Ticket create(Ticket t) {
		if(ticketRepo.existsByViewScheduleAndSeat(t.getViewSchedule(), t.getSeat()))
			throw new EntityExistsException("This seat is already booked.");
		return ticketRepo.save(t);
	}
	
	public Ticket get(long id) {
		if(!ticketRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this ticket.");
		return ticketRepo.findById(id).get();
	}
	
	public Ticket edit(long id, Ticket t) {
		if(!ticketRepo.existsById(id) || id!=t.getId()) throw new EntityNotFoundException("Couldn't find this ticket.");
		return ticketRepo.save(t);
	}
	
	public String remove(long id) {
		if(!ticketRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this ticket.");
		
		LocalDateTime scheduleStartTime=ticketRepo.findById(id).get().getViewSchedule().getStartTime();
		if(Duration.between(LocalDateTime.now(), scheduleStartTime).toHours()<=1)
			throw new CancellationException("You can cancel your booking only within 1 hour of the schedule start time.");
		
		ticketRepo.deleteById(id);
		return "Ticket deleted successfully.";
	}
}
