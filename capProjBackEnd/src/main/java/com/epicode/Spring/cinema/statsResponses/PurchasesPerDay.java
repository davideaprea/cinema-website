package com.epicode.Spring.cinema.statsResponses;

import java.sql.Date;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PurchasesPerDay {
	public Double total;
	public Date purchaseDate;
}
