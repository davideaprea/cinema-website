package com.epicode.Spring.enums;

public enum TicketPrice {
	REGULAR(7.89),
	VIP(9.5),
	TRIDIMENSIONAL(0.5);
	
	private final double value;

	TicketPrice(double d) {
		this.value=d;
	}

	public double getValue() {
		return value;
	}
}
