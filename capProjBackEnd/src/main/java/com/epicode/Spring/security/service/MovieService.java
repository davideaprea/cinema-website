package com.epicode.Spring.security.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.epicode.Spring.security.entity.Image;
import com.epicode.Spring.security.entity.Movie;
import com.epicode.Spring.security.payload.MovieDto;
import com.epicode.Spring.security.payload.MovieResponse;
import com.epicode.Spring.security.repository.MovieRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MovieService {
	@Autowired private MovieRepository movieRepo;
	
	public MovieDto create(MovieDto movieDto) {
		if(movieRepo.existsByTitleAndIsTridimensional(movieDto.getTitle(), movieDto.getIsTridimensional()))
			throw new EntityExistsException("This movie already exists.");
		
		try {
			String fileName=StringUtils.cleanPath(movieDto.getCover().getOriginalFilename());
			String path = "main/resources/Movie covers";
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
			
	        movieRepo.save(movie);
			return movieDto;
		} catch (IOException e) {
            throw new DataIntegrityViolationException("Failed to upload cover image.");
        }
	}
	
	public MovieResponse get(long id) {
		if(!movieRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this movie.");
		Movie movie=movieRepo.findById(id).get();
		return getMovieWithCover(movie);
	}

	public List<MovieResponse> getAll(){
		List<MovieResponse> movies=new ArrayList<MovieResponse>();
		for(Movie movie : movieRepo.findAll()) movies.add(getMovieWithCover(movie));
		return movies;
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
	
	public Movie getMovie(long id) {
		if(!movieRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this movie.");
		return movieRepo.findById(id).get();
	}
	
	public MovieResponse getMovieWithCover(Movie movie) {
		Path path=Paths.get(movie.getCover().getPath());
		
		try {
			Resource resource = new UrlResource(path.toUri());
			if(!resource.exists() || !resource.isReadable())
				throw new EntityNotFoundException("Couldn't retrieve the movie cover.");
			
//			byte[] cover=Files.readAllBytes(path);
			
			return new MovieResponse(
					movie.getId(),
					movie.getTitle(),
					resource,
					movie.getTrailerLink(),
					movie.getReleaseDate(),
					movie.getDuration(),
					movie.getDirector(),
					movie.getActors(),
					movie.getDescription(),
					movie.getGenres(),
					movie.getIsTridimensional()
					);
			
		} catch (IOException e) {
			throw new EntityNotFoundException("Couldn't retrieve the movie cover.");
		}
	}
}
