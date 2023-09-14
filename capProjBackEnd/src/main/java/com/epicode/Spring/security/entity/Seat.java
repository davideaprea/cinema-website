package com.epicode.Spring.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
	
	@Column(nullable = false)
	private Integer nSeat;
	
	@Column(nullable = false)
	private Integer nRow;
}
