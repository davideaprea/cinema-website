package com.epicode.Spring.security.payload;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.core.io.Resource;

import com.epicode.Spring.enums.Genres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MovieResponse {
	private Long id;
	private String title;
	private Resource cover;
	private String trailerLink;
	private LocalDate releaseDate;
	private Integer duration;
	private String director;
	private String actors;
	private String description;
	private Set<Genres> genres;
	private Boolean isTridimensional=false;
}
