package com.epicode.Spring.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.epicode.Spring.enums.HallStatus;
import com.epicode.Spring.security.entity.Movie;
import com.epicode.Spring.security.entity.ViewSchedule;
import com.epicode.Spring.security.payload.MovieResponse;
import com.epicode.Spring.security.payload.ViewScheduleDto;
import com.epicode.Spring.security.payload.ViewScheduleResponse;
import com.epicode.Spring.security.repository.ViewScheduleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ViewScheduleService {
	@Autowired private ViewScheduleRepository viewScheduleRepo;
	@Autowired private MovieService movieService;
	
	public ViewScheduleResponse create(ViewScheduleDto viewScheduleDto) {
		long movieId=viewScheduleDto.getMovie().getId();
		Movie movie=movieService.getMovie(movieId);
		
		ViewSchedule vs=new ViewSchedule();
		vs.setHall(viewScheduleDto.getHall());
		vs.setMovie(movie);
		vs.setStartTime(viewScheduleDto.getStartTime());
		
		if(viewScheduleRepo.checkHallAvailability(vs.getHall(), vs.getStartTime(), vs.getEndTime()))
			throw new DataIntegrityViolationException("This hall is not available at this time.");
		
		if(viewScheduleRepo.checkMovieAvailability(vs.getMovie(), vs.getStartTime(), vs.getEndTime()))
			throw new DataIntegrityViolationException("This movie is not available at this time.");
		
		if(vs.getHall().getStatus().equals(HallStatus.UNDER_MAINTENANCE))
			throw new DataIntegrityViolationException("This hall is under maintenance.");
		
		ViewSchedule vsRes=viewScheduleRepo.save(vs);
		return new ViewScheduleResponse(vsRes.getId(), viewScheduleDto.getMovie(), vsRes.getHall(), vsRes.getStartTime(), vsRes.getEndTime());
	}
	
	public ViewScheduleResponse get(long id) {
		if(!viewScheduleRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this schedule.");
		ViewSchedule vs=viewScheduleRepo.findById(id).get();
		MovieResponse movieResponse=movieService.getMovieWithCover(vs.getMovie());
		return new ViewScheduleResponse(vs.getId(), movieResponse, vs.getHall(), vs.getStartTime(), vs.getEndTime());
	}
	
	public List<ViewScheduleResponse> getMovieSchedules(long id){
		Movie movie=movieService.getMovie(id);
		List<ViewSchedule> movieSchedules=viewScheduleRepo.findMovieSchedules(movie);
		
		List<ViewScheduleResponse> movieWithCoverSchedules=new ArrayList<ViewScheduleResponse>();
		for(ViewSchedule v : movieSchedules) movieWithCoverSchedules.add(get(v.getId()));
		return movieWithCoverSchedules;
	}
	
	public List<MovieResponse> getScheduledMovies() {
		List<Movie> scheduledMovies= viewScheduleRepo.scheduledMovies();
		List<MovieResponse> getMoviesWithCover=new ArrayList<MovieResponse>();
		for(Movie m : scheduledMovies) getMoviesWithCover.add(movieService.getMovieWithCover(m));
		return getMoviesWithCover;
	}
	
	public ViewSchedule edit(long id, ViewSchedule vs) {
		if(!viewScheduleRepo.existsById(id) || id!=vs.getId()) throw new EntityNotFoundException("Couldn't find this schedule.");
		return viewScheduleRepo.save(vs);
	}
	
	public String remove(long id) {
		if(!viewScheduleRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this schedule.");
		viewScheduleRepo.deleteById(id);
		return "Schedule deleted successfully.";
	}
}
