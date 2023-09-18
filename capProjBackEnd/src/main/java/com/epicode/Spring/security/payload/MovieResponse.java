package com.epicode.Spring.security.payload;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import com.epicode.Spring.enums.Genres;
import com.epicode.Spring.security.classes.ResourceSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
//	@JsonSerialize(using=ResourceSerializer.class)
//	private ByteArrayResource cover;
//	@JsonSerialize(using=ResourceSerializer.class)
	private Resource cover;
//	private byte[] cover;
	private String trailerLink;
	private LocalDate releaseDate;
	private Integer duration;
	private String director;
	private String actors;
	private String description;
	private Set<Genres> genres;
	private Boolean isTridimensional=false;
}
