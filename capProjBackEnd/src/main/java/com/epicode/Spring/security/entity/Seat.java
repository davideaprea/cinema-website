package com.epicode.Spring.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Embeddable
public class Seat {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	
//	@ManyToOne
//	private Hall hall;
	
	@Column(nullable = false)
	private Integer nSeat;
	
	@Column(nullable = false)
	private Integer nRow;

//	public void setHall(Hall hall) {
//		this.hall = hall;
//	}
}
