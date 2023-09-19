package com.epicode.Spring.security.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.epicode.Spring.security.entity.Image;
import com.epicode.Spring.security.entity.Movie;
import com.epicode.Spring.security.payload.MovieDto;
import com.epicode.Spring.security.repository.MovieRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MovieService {
	@Autowired private MovieRepository movieRepo;
	
	public Movie create(MovieDto movieDto) {
		if(movieRepo.existsByTitleAndIsTridimensional(movieDto.getTitle(), movieDto.getIsTridimensional()))
			throw new EntityExistsException("This movie already exists.");
		
		try {
			String fileName=StringUtils.cleanPath(movieDto.getCover().getOriginalFilename());
			String path = System.getProperty("user.home") + "/Desktop/Movie covers";
			String coverImageName = UUID.randomUUID().toString() + "_" + fileName;
			Path coverImagePath = Path.of(path, coverImageName);
			if (!Files.exists(coverImagePath.getParent())) Files.createDirectories(coverImagePath.getParent());
			Files.copy(movieDto.getCover().getInputStream(), coverImagePath, StandardCopyOption.REPLACE_EXISTING);
			
			Image cover = new Image();
	        cover.setName(coverImageName);
	        cover.setPath(coverImagePath.toString());
	        
	        Movie movie = new Movie();
	        movie.setTitle(movieDto.getTitle());
	        movie.setCover(cover);
	        movie.setTrailerLink(movieDto.getTrailerLink());
	        movie.setReleaseDate(movieDto.getReleaseDate());
	        movie.setDuration(movieDto.getDuration());
	        movie.setDirector(movieDto.getDirector());
	        movie.setActors(movieDto.getActors());
	        movie.setDescription(movieDto.getDescription());
	        movie.setGenres(movieDto.getGenres());
	        movie.setIsTridimensional(movieDto.getIsTridimensional());
			
	        return movieRepo.save(movie);
		} catch (IOException e) {
            throw new DataIntegrityViolationException("Failed to upload cover image.");
        }
	}
	
	public Movie get(long id) {
		if(!movieRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this movie.");
		return movieRepo.findById(id).get();
	}

	public List<Movie> getAll(){
		return (List<Movie>) movieRepo.findAll();
	}
	
	public Movie edit(long id, Movie m) {
		if(!movieRepo.existsById(id) || id!=m.getId()) throw new EntityNotFoundException("Couldn't find this movie.");
		return movieRepo.save(m);
	}
	
	public String remove(long id) {
		if(!movieRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this movie.");
		movieRepo.deleteById(id);
		return "Movie deleted successfully.";
	}
}
