package com.epicode.Spring.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.epicode.Spring.security.entity.Ticket;
import com.epicode.Spring.security.entity.ViewSchedule;
import com.epicode.Spring.security.entity.Seat;


public interface TicketRepository extends CrudRepository<Ticket, Long>{
	public boolean existsByViewScheduleAndSeat(ViewSchedule viewSchedule, Seat seat);
}
