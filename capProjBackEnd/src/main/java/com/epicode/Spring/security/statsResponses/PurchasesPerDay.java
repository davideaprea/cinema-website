package com.epicode.Spring.security.statsResponses;

import java.sql.Date;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PurchasesPerDay {
	public Double total;
	public Date purchaseDate;
}
