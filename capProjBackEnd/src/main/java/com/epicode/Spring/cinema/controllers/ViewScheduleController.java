package com.epicode.Spring.cinema.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.Spring.cinema.entities.Movie;
import com.epicode.Spring.cinema.entities.ViewSchedule;
import com.epicode.Spring.cinema.services.ViewScheduleService;

@RestController
@RequestMapping("/schedules")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ViewScheduleController {
	@Autowired private ViewScheduleService scheduleService;
	
	@PostMapping
	@PreAuthorize("hasRole('MODERATOR') OR hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody ViewSchedule vs) {
        return new ResponseEntity<ViewSchedule>(scheduleService.create(vs), HttpStatus.CREATED);
    }
	
	@GetMapping("/next-schedules")
    public ResponseEntity<?> getNextSchedules() {
        return new ResponseEntity<List<ViewSchedule>>(scheduleService.getNextSchedules(), HttpStatus.OK);
    }
	
	@GetMapping("/current-schedules")
    public ResponseEntity<?> getCurrentSchedules() {
        return new ResponseEntity<List<ViewSchedule>>(scheduleService.getCurrentSchedules(), HttpStatus.OK);
    }
	
	@GetMapping("/scheduled-movies")
    public ResponseEntity<?> getScheduledMovies() {
        return new ResponseEntity<List<Movie>>(scheduleService.getScheduledMovies(), HttpStatus.OK);
    }
	
	@GetMapping("/movie-schedules/{id}")
    public ResponseEntity<?> getMovieSchedules(@PathVariable long id) {
        return new ResponseEntity<List<ViewSchedule>>(scheduleService.getMovieSchedules(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return new ResponseEntity<ViewSchedule>(scheduleService.get(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') OR hasRole('ADMIN')")
    public ResponseEntity<?> edit(@PathVariable long id, @RequestBody ViewSchedule vs) {
        return new ResponseEntity<ViewSchedule>(scheduleService.edit(id, vs), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') OR hasRole('ADMIN')")
    public ResponseEntity<String> remove(@PathVariable long id) {
        return new ResponseEntity<String>(scheduleService.remove(id), HttpStatus.OK);
    }
    
    @DeleteMapping
    @PreAuthorize("hasRole('MODERATOR') OR hasRole('ADMIN')")
    public ResponseEntity<?> remove(@RequestBody Set<Long> ids) {
    	ids.forEach(id -> scheduleService.remove(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
