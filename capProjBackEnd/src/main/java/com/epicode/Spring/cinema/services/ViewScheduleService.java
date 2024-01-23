package com.epicode.Spring.cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.epicode.Spring.cinema.enums.HallStatus;
import com.epicode.Spring.cinema.entities.Movie;
import com.epicode.Spring.cinema.entities.ViewSchedule;
import com.epicode.Spring.cinema.repositories.ViewScheduleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ViewScheduleService {
	@Autowired private ViewScheduleRepository viewScheduleRepo;
	@Autowired private MovieService movieService;
	
	public ViewSchedule create(ViewSchedule vs) {
		
		if(viewScheduleRepo.checkHallAvailability(vs.getHall(), vs.getStartTime(), vs.getEndTime()))
			throw new DataIntegrityViolationException("This hall is not available at this time.");
		
		if(viewScheduleRepo.checkMovieAvailability(vs.getMovie(), vs.getStartTime(), vs.getEndTime()))
			throw new DataIntegrityViolationException("This movie is not available at this time.");
		
		if(vs.getHall().getStatus().equals(HallStatus.UNDER_MAINTENANCE))
			throw new DataIntegrityViolationException("This hall is under maintenance.");
		
		return viewScheduleRepo.save(vs);
	}
	
	public ViewSchedule get(long id) {
		if(!viewScheduleRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this schedule.");
		return viewScheduleRepo.findById(id).get();
	}
	
	public List<ViewSchedule> getMovieSchedules(long id){
		Movie movie=movieService.get(id);
		return viewScheduleRepo.findMovieSchedules(movie);
	}
	
	public List<ViewSchedule> getNextSchedules(){
		return viewScheduleRepo.findNextSchedules();
	}
	
	public List<ViewSchedule> getCurrentSchedules(){
		return viewScheduleRepo.findCurrentSchedules();
	}
	
	public List<Movie> getScheduledMovies() {
		return viewScheduleRepo.scheduledMovies();
	}
	
	public ViewSchedule edit(long id, ViewSchedule vs) {
		if(!viewScheduleRepo.existsById(id) || id!=vs.getId()) throw new EntityNotFoundException("Couldn't find this schedule.");
		
		if(viewScheduleRepo.checkHallAvailability(vs.getId(), vs.getHall(), vs.getStartTime(), vs.getEndTime()))
			throw new DataIntegrityViolationException("This hall is not available at this time.");
		
		if(viewScheduleRepo.checkMovieAvailability(vs.getId(), vs.getMovie(), vs.getStartTime(), vs.getEndTime()))
			throw new DataIntegrityViolationException("This movie is not available at this time.");
		
		if(vs.getHall().getStatus().equals(HallStatus.UNDER_MAINTENANCE))
			throw new DataIntegrityViolationException("This hall is under maintenance.");
		
		return viewScheduleRepo.save(vs);
	}
	
	public String remove(long id) {
		if(!viewScheduleRepo.existsById(id)) throw new EntityNotFoundException("Couldn't find this schedule.");
		viewScheduleRepo.deleteById(id);
		return "Schedule deleted successfully.";
	}
}
