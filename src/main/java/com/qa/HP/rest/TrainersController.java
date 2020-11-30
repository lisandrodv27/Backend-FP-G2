package com.qa.HP.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.qa.HP.domain.Trainers;
import com.qa.HP.service.TrainersService;

// This class deals with requests
public class TrainersController {
	
	private TrainersService service;
	
	public TrainersController(TrainersService service) {
		super();
		this.service = service;
	}
	
	//Get request
		@GetMapping("/get")
		public ResponseEntity<List<Trainers>> getTrainers() {
			return ResponseEntity.ok(this.service.getTrainers());
			
		}
		
		@PostMapping("/create")
		public ResponseEntity<Trainers> createTrainers(@RequestBody Trainers trainer) {
			return new ResponseEntity<Trainers>(this.service.createTrainers(trainer), HttpStatus.CREATED);
		}

}
