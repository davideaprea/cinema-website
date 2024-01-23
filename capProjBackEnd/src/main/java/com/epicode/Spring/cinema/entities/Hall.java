package com.epicode.Spring.cinema.entities;

import org.springframework.dao.DataIntegrityViolationException;

import com.epicode.Spring.cinema.enums.HallStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="halls")
public class Hall {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("nrows")
	@Column(nullable = false)
	private Integer nRows;
	
	@JsonProperty("nseatsPerRow")
	@Column(nullable = false)
	private Integer nSeatsPerRow;
	
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private HallStatus status=HallStatus.AVAILABLE;

	public void setnRows(Integer nRows) {
		if(!nRows.equals(null) && nRows>0) this.nRows = nRows;
		else throw new DataIntegrityViolationException("Please, insert a correct amount of rows.");
	}

	public void setnSeatsPerRow(Integer nSeatsPerRow) {
		if(!nSeatsPerRow.equals(null) && nSeatsPerRow>0) this.nSeatsPerRow = nSeatsPerRow;
		else throw new DataIntegrityViolationException("Please, insert a correct amount of seats.");
	}

	public void setStatus(HallStatus status) {
		if(!status.equals(null)) this.status = status;
	}
}
