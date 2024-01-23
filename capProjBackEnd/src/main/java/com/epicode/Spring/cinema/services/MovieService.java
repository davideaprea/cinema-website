package com.epicode.Spring.cinema.services;

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
import org.springframework.web.multipart.MultipartFile;

import com.epicode.Spring.cinema.entities.Movie;
import com.epicode.Spring.cinema.payloads.MovieDto;
import com.epicode.Spring.cinema.repositories.MovieRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MovieService {
	@Autowired private MovieRepository movieRepo;
	
	public Movie create(MovieDto movieDto) {
		if(movieRepo.existsByTitleAndIsTridimensional(movieDto.getTitle(), movieDto.getIsTridimensional()))
			throw new EntityExistsException("This movie already exists.");
		
		try {
			final String baseImageUrl="http://localhost:8080/movies/cover/";
	        
	        Movie movie = new Movie();
	        movie.setTitle(movieDto.getTitle());
	        movie.setCover(baseImageUrl+saveFileOnFolder(movieDto.getCover()));
	        movie.setBackgroundCover(baseImageUrl+saveFileOnFolder(movieDto.getBackgroundCover()));
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
	
	private String saveFileOnFolder(MultipartFile file) throws IOException {
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		String path = System.getProperty("user.home") + "/Desktop/Movie covers";
		String coverImageName = UUID.randomUUID().toString() + "_" + fileName;
		Path coverImagePath = Path.of(path, coverImageName);
		if (!Files.exists(coverImagePath.getParent())) Files.createDirectories(coverImagePath.getParent());
		Files.copy(file.getInputStream(), coverImagePath, StandardCopyOption.REPLACE_EXISTING);
		return coverImageName;
	}
}
