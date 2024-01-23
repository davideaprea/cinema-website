package com.epicode.Spring.cinema.entities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;

import com.epicode.Spring.cinema.enums.TicketPrice;
import com.epicode.Spring.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Entity
@ToString
@Table(name="receipts")
public class Receipt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
	private Set<Booking> bookings;
	
	@Column(nullable=false, updatable = false)
	private LocalDateTime purchaseTime;
	
	@Column(nullable=false)
	private Double totPrice;
	
	public void setUser(User user) {
		if(!user.equals(null)) this.user = user;
		else throw new DataIntegrityViolationException("Please, specify the customer for this ticket.");
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
		for(Booking b : bookings) b.setReceipt(this);
		
		int selectedRow=bookings.iterator().next().getSeat().getNRow();
		int hallRows=bookings.iterator().next().getViewSchedule().getHall().getNRows();
		
		double totPrice=hallRows-selectedRow<=2 ? bookings.size()*TicketPrice.VIP.getValue() : bookings.size()*TicketPrice.REGULAR.getValue();
		if(bookings.iterator().next().getViewSchedule().getMovie().getIsTridimensional()) this.totPrice+=bookings.size()*TicketPrice.TRIDIMENSIONAL.getValue();
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#.00", symbols);
		String formatPrice=df.format(totPrice);
		this.totPrice=Double.parseDouble(formatPrice);
		
		this.purchaseTime = LocalDateTime.now();
	}
}
