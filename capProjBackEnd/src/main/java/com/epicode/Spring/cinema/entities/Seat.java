package com.epicode.Spring.cinema.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Embeddable
public class Seat {
	
	@Column(nullable = false)
	private Integer nSeat;
	
	@Column(nullable = false)
	private Integer nRow;
}
