package com.epicode.Spring.security.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.epicode.Spring.security.entity.Movie;
import com.epicode.Spring.security.payload.MovieDto;
import com.epicode.Spring.security.service.MovieService;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MovieController {
	@Autowired private MovieService movieService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@ModelAttribute MovieDto m) {
        return new ResponseEntity<MovieDto>(movieService.create(m), HttpStatus.CREATED);
    }
	
	@PostMapping("/uploadtest")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createTest(@RequestBody MultipartFile cover) {
		try {
			String fileName=StringUtils.cleanPath(cover.getOriginalFilename());
			String path = System.getProperty("user.home") + "/Desktop/Movie covers";
			String coverImageName = UUID.randomUUID().toString() + "_" + fileName;
			Path coverImagePath = Path.of(path, coverImageName);
			if (!Files.exists(coverImagePath.getParent())) Files.createDirectories(coverImagePath.getParent());
			Files.copy(cover.getInputStream(), coverImagePath, StandardCopyOption.REPLACE_EXISTING);
	        return ResponseEntity.ok().body(fileName);
		}
        catch (IOException e) {
        	return ResponseEntity.badRequest().body(e.getMessage());
		}
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getById(@PathVariable long id) {
//        return new ResponseEntity<MovieDto>(movieService.get(id), HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> edit(@PathVariable long id, @RequestBody Movie m) {
        return new ResponseEntity<Movie>(movieService.edit(id, m), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> remove(@PathVariable long id) {
        return new ResponseEntity<String>(movieService.remove(id), HttpStatus.OK);
    }
}
