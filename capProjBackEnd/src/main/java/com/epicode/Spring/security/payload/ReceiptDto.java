package com.epicode.Spring.security.payload;

import java.util.Set;

import com.epicode.Spring.security.entity.Booking;

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
