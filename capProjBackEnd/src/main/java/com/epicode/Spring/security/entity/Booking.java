package com.epicode.Spring.security.entity;

import org.springframework.dao.DataIntegrityViolationException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="bookings", uniqueConstraints = @UniqueConstraint(columnNames = {"seat_id", "view_schedule_id"}))
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ViewSchedule viewSchedule;
	
	@ManyToOne
	private Seat seat;
	
	@ManyToOne
	private Receipt receipt;

	public void setViewSchedule(ViewSchedule viewSchedule) {
		if(!viewSchedule.equals(null)) this.viewSchedule = viewSchedule;
		else throw new DataIntegrityViolationException("Please, specify the view schedule.");
	}

	public void setSeat(Seat seat) {
		if(!seat.equals(null)) this.seat = seat;
		else throw new DataIntegrityViolationException("Please, specify a seat for this booking.");
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
}
