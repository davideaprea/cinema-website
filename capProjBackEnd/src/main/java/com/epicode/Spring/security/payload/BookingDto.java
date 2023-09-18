package com.epicode.Spring.security.payload;

import com.epicode.Spring.security.entity.Seat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
	private Long scheduleId;
	private Seat seat;
}
