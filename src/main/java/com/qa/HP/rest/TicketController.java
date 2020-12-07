package com.qa.HP.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.HP.domain.Ticket;
import com.qa.HP.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	private TicketService service;

	private TicketController(TicketService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
		return new ResponseEntity<Ticket>(this.service.createTicket(ticket), HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Ticket>> getTicket() {
		return ResponseEntity.ok(this.service.getTicket());
	}
	
	@GetMapping("/findByTopic/{topic}")
	public ResponseEntity<List<Ticket>> findTicketByTopic(@PathVariable String topic) {
		return ResponseEntity.ok(this.service.findTicketbyTopic(topic));
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Object> deleteTicket(@PathVariable Long ticketId) {
		if (this.service.deleteTicket(ticketId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathParam("id") Long ticketId) {
		return new ResponseEntity<Ticket>(this.service.updateTicket(ticket, ticketId), HttpStatus.ACCEPTED);
	}

}
