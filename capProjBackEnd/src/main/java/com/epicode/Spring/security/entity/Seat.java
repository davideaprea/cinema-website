package com.epicode.Spring.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="hall_seats")
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Hall hall;
	
	@Column(nullable = false)
	private Integer nSeat;
	
	@Column(nullable = false)
	private Integer nRow;

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public void setNSeat(Integer nSeat) {
		this.nSeat = nSeat;
	}

	public void setNRow(Integer nRow) {
		this.nRow = nRow;
	}
}
