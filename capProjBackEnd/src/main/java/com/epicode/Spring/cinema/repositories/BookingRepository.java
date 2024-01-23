package com.epicode.Spring.cinema.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.epicode.Spring.cinema.entities.ViewSchedule;
import com.epicode.Spring.cinema.entities.Booking;
import com.epicode.Spring.cinema.entities.Seat;
import java.util.List;
import java.util.Map;



public interface BookingRepository extends CrudRepository<Booking, Long>{
	public boolean existsByViewScheduleAndSeat(ViewSchedule viewSchedule, Seat seat);
	public List<Booking> findByViewSchedule(ViewSchedule viewSchedule);

	@Query("SELECT "
			+ "(COUNT(b) * 100 / (b.viewSchedule.hall.nRows * b.viewSchedule.hall.nSeatsPerRow)), "
			+ "b.viewSchedule "
			+ "FROM Booking b "
			+ "WHERE FUNCTION('timestampdiff', DAY, CURRENT_TIMESTAMP, b.viewSchedule.startTime) <= 7 "
			+ "GROUP BY b.viewSchedule")
	public Map<Double, ViewSchedule> getSchedulesBookingPercentage();
}
