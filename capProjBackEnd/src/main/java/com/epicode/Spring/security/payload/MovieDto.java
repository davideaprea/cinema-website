package com.epicode.Spring.security.payload;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.epicode.Spring.enums.Genres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
	private String title;
	private MultipartFile cover;
	private String trailerLink;
	private LocalDate releaseDate;
	private Integer duration;
	private String director;
	private String actors;
	private String description;
	private Set<Genres> genres;
	private Boolean isTridimensional=false;
}
