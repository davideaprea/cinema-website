package com.epicode.Spring.security.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.epicode.Spring.security.entity.Image;
import com.epicode.Spring.security.entity.Movie;
import com.epicode.Spring.security.payload.MovieDto;
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
			String path = System.getProperty("user.home") + "/Desktop/Programming";
			String coverImageName = UUID.randomUUID().toString() + "_" + movieDto.getCover().getOriginalFilename();
			Path coverImagePath = Path.of(path, coverImageName);
			Files.createDirectories(coverImagePath.getParent());
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
			
	        Movie mRes=movieRepo.save(movie);
			movieDto.setId(mRes.getId());
			return movieDto;
		} catch (IOException e) {
            throw new RuntimeException("Failed to upload cover image.");
        }
	}
	
//	public MovieDto get(long id) {
//		if(!movieRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this movie.");
//		Movie movie=movieRepo.findById(id).get();
//		
//		try {
//			Path imagePath = Path.of(System.getProperty("user.home") + "/Desktop/Programming", movie.getCover().getPath());
//	        byte[] b=Files.readAllBytes(imagePath);
//	        MultipartFile m=new ByteArrayM
//	        
//	        return new MovieDto(movie.getId(), movie.getTitle(), null, null, null, null, null, null, null, null, null);
//		} catch(IOException e) {
//			throw new RuntimeException("Failed to read cover image.");
//		}
//	}
	
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
