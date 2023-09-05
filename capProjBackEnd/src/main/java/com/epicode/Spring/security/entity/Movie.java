package com.epicode.Spring.security.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="movies", uniqueConstraints = @UniqueConstraint(columnNames = {"title", "isTridimensional"}))
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private LocalDate releaseDate;
	
	@Column(nullable = false)
	private Integer duration;
	
	@Column(nullable = false)
	private String director;
	
	@Column(nullable = false)
	private String actors;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private Set<Genres> genres;
	
	@Column(nullable = false)
	private Boolean isTridimensional;
}
