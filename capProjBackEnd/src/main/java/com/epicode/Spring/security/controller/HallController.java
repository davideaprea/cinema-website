package com.epicode.Spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.Spring.security.entity.Hall;
import com.epicode.Spring.security.service.HallService;

@RestController
@RequestMapping("/halls")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HallController {
	@Autowired private HallService hallService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody Hall h) {
        return new ResponseEntity<Hall>(hallService.create(h), HttpStatus.CREATED);
    }
	
	@GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<List<Hall>>(hallService.getAll(), HttpStatus.OK);
    }
	
	@GetMapping("/available-halls")
    public ResponseEntity<?> getAllAvailable() {
        return new ResponseEntity<List<Hall>>(hallService.getAllAvailableHalls(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return new ResponseEntity<Hall>(hallService.get(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> edit(@PathVariable long id, @RequestBody Hall h) {
        return new ResponseEntity<Hall>(hallService.edit(id, h), HttpStatus.OK);
    }
}
