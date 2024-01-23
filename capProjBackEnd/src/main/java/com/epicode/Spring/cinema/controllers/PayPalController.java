package com.epicode.Spring.cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.Spring.cinema.payloads.ReceiptDto;
import com.epicode.Spring.cinema.services.PayPalService;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PayPalController {
	@Autowired private PayPalService payPalService;
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> create(@RequestBody ReceiptDto r) {
        return new ResponseEntity<String>(payPalService.createOrder(r), HttpStatus.CREATED);
    }
	
	@PostMapping("/{orderId}")
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> create(@PathVariable String orderId) {
        return new ResponseEntity<String>(payPalService.captureOrder(orderId), HttpStatus.CREATED);
    }
}
