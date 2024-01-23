package com.epicode.Spring.cinema.entities;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="views_schedules")
public class ViewSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Movie movie;
	
	@ManyToOne
	private Hall hall;
	
	@Column(nullable = false)
	private LocalDateTime startTime;
	
	@Column(nullable = false)
	private LocalDateTime endTime;

	public void setMovie(Movie movie) {
		if(!movie.equals(null)) this.movie = movie;
		else throw new DataIntegrityViolationException("Please, specify a movie for this view schedule.");
	}

	public void setHall(Hall hall) {
		if(!hall.equals(null)) this.hall = hall;
		else throw new DataIntegrityViolationException("Please, specify a hall for this view schedule.");
	}
	
	public void setStartTime(LocalDateTime startTime) {
		if(!startTime.equals(null) && startTime.isAfter(LocalDateTime.now())) {
			this.startTime = startTime;
			endTime=startTime.plusMinutes(movie.getDuration());
		}
		else throw new DataIntegrityViolationException("Please, insert a correct start time for this view schedule.");
	}
}
