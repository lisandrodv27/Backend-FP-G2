package com.qa.HP.service;

import java.util.List;

import com.qa.HP.domain.Trainers;
import com.qa.HP.domain.TrainersRepo;

public class TrainersService {
	
	private TrainersRepo repo;
	
	public TrainersService (TrainersRepo repo) {
		super();
		this.repo = repo;
	}
	

	public void setRepo(TrainersRepo repo) {
		this.repo = repo;
	}
	
	//Get request
		public List<Trainers> getTrainers() {
			return this.repo.findAll();
			
		}
	
		public Trainers createTrainers(Trainers trainer) {
		return this.repo.save(trainer);
	}
	

}
