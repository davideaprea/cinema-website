package com.epicode.Spring.security.payload;

import java.time.LocalDate;
import java.util.Set;

import com.epicode.Spring.enums.Genres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
	private Long id;
	private String title;
	//@JsonSerialize(using=ResourceSerializer.class)
	private byte[] cover;
	private String trailerLink;
	private LocalDate releaseDate;
	private Integer duration;
	private String director;
	private String actors;
	private String description;
	private Set<Genres> genres;
	private Boolean isTridimensional=false;
}
