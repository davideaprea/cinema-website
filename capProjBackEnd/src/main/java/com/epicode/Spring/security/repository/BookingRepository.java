package com.epicode.Spring.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.epicode.Spring.security.entity.ViewSchedule;
import com.epicode.Spring.security.entity.Booking;
import com.epicode.Spring.security.entity.Seat;


public interface BookingRepository extends CrudRepository<Booking, Long>{
	public boolean existsByViewScheduleAndSeat(ViewSchedule viewSchedule, Seat seat);
}
