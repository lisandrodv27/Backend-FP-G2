package com.qa.HP.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trainer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String FirstName;
	
	private String LastName;
	
	private String Password;
	
	
	public Trainer(String FirstName, String LastName, String Password) {
		super();
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Password = Password;
		
	}
	
	// REQUIRED
	public Trainer() { 
		super();

	}
	@Override
	public String toString() {
		return "Trainers [id=" + id + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Password=" + Password + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	

}
