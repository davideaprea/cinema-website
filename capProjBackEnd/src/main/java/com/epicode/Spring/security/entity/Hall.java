package com.epicode.Spring.security.entity;

import org.springframework.dao.DataIntegrityViolationException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	
	@Column(nullable = false)
	private Integer nRows;
	
	@Column(nullable = false)
	private Integer nSeatsPerRows;

	public void setnRows(Integer nRows) {
		if(!nRows.equals(null) && nRows>0) this.nRows = nRows;
		else throw new DataIntegrityViolationException("Please, insert the correct number for the rows.");
	}

	public void setnSeatsPerRows(Integer nSeatsPerRows) {
		if(!nRows.equals(null) && nSeatsPerRows>0) this.nSeatsPerRows = nSeatsPerRows;
		else throw new DataIntegrityViolationException("Please, insert the correct number for the seats.");
	}
}
