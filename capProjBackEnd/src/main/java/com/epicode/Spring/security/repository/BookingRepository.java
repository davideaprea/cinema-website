package com.epicode.Spring.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.epicode.Spring.security.entity.ViewSchedule;
import com.epicode.Spring.security.entity.Booking;
import com.epicode.Spring.security.entity.Seat;
import java.util.List;



public interface BookingRepository extends CrudRepository<Booking, Long>{
	public boolean existsByViewScheduleAndSeat(ViewSchedule viewSchedule, Seat seat);
	public List<Booking> findByViewSchedule(ViewSchedule viewSchedule);
}
