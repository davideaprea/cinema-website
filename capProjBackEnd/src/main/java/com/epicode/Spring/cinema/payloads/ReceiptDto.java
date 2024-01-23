package com.epicode.Spring.cinema.payloads;

import java.util.Set;

import com.epicode.Spring.cinema.entities.Booking;
import com.epicode.Spring.security.payload.JWTAuthResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDto {
	private JWTAuthResponse user;
	private Set<Booking> bookings;
}
