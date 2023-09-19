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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epicode.Spring.security.entity.Receipt;
import com.epicode.Spring.security.service.ReceiptService;

@RestController
@RequestMapping("/receipts")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReceiptController {
	@Autowired private ReceiptService receiptService;
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> create(@RequestBody Receipt r) {
        return new ResponseEntity<Receipt>(receiptService.create(r), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return new ResponseEntity<Receipt>(receiptService.get(id), HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> edit(@PathVariable long id, @RequestBody Receipt r) {
//        return new ResponseEntity<Receipt>(receiptService.edit(id, r), HttpStatus.OK);
//    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> remove(@PathVariable long id) {
        return new ResponseEntity<String>(receiptService.remove(id), HttpStatus.OK);
    }
}
