package com.epicode.Spring.security.controller;

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

import com.epicode.Spring.security.entity.ViewSchedule;
import com.epicode.Spring.security.payload.ViewScheduleDto;
import com.epicode.Spring.security.payload.ViewScheduleResponse;
import com.epicode.Spring.security.service.ViewScheduleService;

@RestController
@RequestMapping("/schedules")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ViewScheduleController {
	@Autowired private ViewScheduleService scheduleService;
	
	@PostMapping
	@PreAuthorize("hasRole('MODERATOR') OR hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody ViewScheduleDto vs) {
        return new ResponseEntity<ViewScheduleResponse>(scheduleService.create(vs), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return new ResponseEntity<ViewScheduleResponse>(scheduleService.get(id), HttpStatus.OK);
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
}
