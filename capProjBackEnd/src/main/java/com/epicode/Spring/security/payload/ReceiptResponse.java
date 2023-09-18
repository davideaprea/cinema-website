package com.epicode.Spring.security.payload;

import java.time.LocalDateTime;
import java.util.Set;

import com.epicode.Spring.security.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptResponse {
	private Long id;
	private User user;
	private Set<BookingResponse> bookings;
	private LocalDateTime purchaseTime;
	private Double totPrice;
}
