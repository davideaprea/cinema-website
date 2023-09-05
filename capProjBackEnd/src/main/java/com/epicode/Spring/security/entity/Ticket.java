package com.epicode.Spring.security.entity;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;

import com.epicode.Spring.enums.TicketPrice;

import jakarta.persistence.Column;
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
@Table(name="tickets", uniqueConstraints = @UniqueConstraint(columnNames = {"seat_id", "view_schedule_id"}))
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private ViewSchedule viewSchedule;
	
	@ManyToOne
	private Seat seat;
	
	@Column(nullable=false, updatable = false)
	private LocalDateTime purchaseTime;
	
	@Column(nullable=false)
	private Double price;

	public void setUser(User user) {
		if(!user.equals(null)) this.user = user;
		else throw new DataIntegrityViolationException("Please, specify the customer for this ticket.");
	}

	public void setViewSchedule(ViewSchedule viewSchedule) {
		if(!viewSchedule.equals(null)) this.viewSchedule = viewSchedule;
		else throw new DataIntegrityViolationException("Please, specify the view schedule.");
	}

	public void setSeat(Seat seat) {
		if(!seat.equals(null)) {
			this.seat = seat;
			if(viewSchedule.getHall().getNRows()-seat.getNRow()<=2) this.price=TicketPrice.VIP.getValue();
			else this.price=TicketPrice.REGULAR.getValue();
		}
		else throw new DataIntegrityViolationException("Please, specify a seat for this ticket.");
	}

	public void setPurchaseTime() {
		this.purchaseTime = LocalDateTime.now();
	}
}
