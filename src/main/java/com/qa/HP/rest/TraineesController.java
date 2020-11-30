package com.qa.HP.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.qa.HP.domain.Trainees;
import com.qa.HP.service.TraineesService;

public class TraineesController {
	
	private TraineesService service;

	private TraineesController(TraineesService service) {
		super();
		this.service = service;
	}

	//Create a trainee- signing up
	@PostMapping("/create")
	public ResponseEntity<Trainees> createTrainee(@RequestBody Trainees trainee) {
		return new ResponseEntity<Trainees>(this.service.createTrainee(trainee), HttpStatus.CREATED);
	}
	//Get trainee name when signing in
	@GetMapping("/get")
	public ResponseEntity<List<Trainees>> getTrainee() {
		return ResponseEntity.ok(this.service.getTrainee());
	}

	
	//@PutMapping("/update")
	//public ResponseEntity<Trainee> updateTicket(@RequestBody Trainee trainee, @PathParam("id") Long id) {
	//	return new ResponseEntity<Trainee>(this.service.updateTrainee(trainee, id), HttpStatus.ACCEPTED);
	//}

}
