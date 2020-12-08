package com.qa.HP;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.HP.domain.Ticket;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class TicketIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void getTicketTest() throws Exception {
		Ticket ticket = new Ticket("A title", "An issue", "A topic", "21/12/2021", 4, "Pending", 123, 456);
		ticket.setTicketId(1L);
		List<Ticket> tickets = new ArrayList<>();
		tickets.add(ticket);
		String getResponseBody = this.mapper.writeValueAsString(ticket);
		this.mockMVC.perform(get("/get")).andExpect(status().isOk()).andExpect(content().json(getResponseBody));
	}
	
	@Test
	void getTicketByTopicTest() throws Exception {
		Ticket ticket = new Ticket("A title", "An issue", "A topic", "21/12/2021", 4, "Pending", 123, 456);
		String topic = "Dev Ops";
		List<Ticket> tickets = new ArrayList<>();
		tickets.add(ticket);
		String responseBody = this.mapper.writeValueAsString(tickets);
		RequestBuilder request = get("/ticket/findByTopic/" + topic).contentType(MediaType.APPLICATION_JSON).content(responseBody);
		ResultMatcher checkStatus = status().isOk();
		this.mockMVC.perform(request).andExpect(checkStatus);
	}
	
	@Test
	void getTicketByTrainee() throws Exception {
		Ticket ticket = new Ticket("A title", "An issue", "A topic", "21/12/2021", 4, "Pending", 123, 456);
	}

	@Test
	void createTicketTest() throws Exception {
		Ticket newTicket = new Ticket("A title", "An issue", "A topic", "21/12/2021", 4, "Pending", 123, 456);
		String body = this.mapper.writeValueAsString(newTicket);
		RequestBuilder createRequestBody = post("/ticket/create").contentType(MediaType.APPLICATION_JSON).content(body);

		ResultMatcher checkStatus = status().isCreated();

		Ticket savedTicket = new Ticket("A title", "An issue", "A topic", "21/12/2021", 4, "Pending", 123, 456);
		savedTicket.setTicketId(2L);

		String resultBody = this.mapper.writeValueAsString(savedTicket);
		ResultMatcher checkBody = content().json(resultBody);

		this.mockMVC.perform(createRequestBody).andExpect(checkStatus).andExpect(checkBody);

		MvcResult result = this.mockMVC.perform(createRequestBody).andExpect(checkStatus).andReturn();
	}

	@Test
	void deleteTicketTest() throws Exception {
		RequestBuilder deleteRequest = delete("/ticket/remove/1");
		ResultMatcher checkStatus = status().is(200); //

		this.mockMVC.perform(deleteRequest).andExpect(checkStatus);
	}

	@Test
	void updateTicketTest() throws Exception {
		Ticket newTicket = new Ticket("A title", "An issue", "A topic", "21/12/2021", 4, "Pending", 123, 456);
		String body = this.mapper.writeValueAsString(newTicket);
		RequestBuilder updateRequestBody = put("/ticket/update?id=1").contentType(MediaType.APPLICATION_JSON).content(body);

		ResultMatcher checkStatus = status().isAccepted();

		Ticket savedTicket = new Ticket("A title", "An issue", "A topic", "21/12/2021", 4, "Pending", 123, 456);
		savedTicket.setTicketId(1L);

		String resultBody = this.mapper.writeValueAsString(savedTicket);
		ResultMatcher checkBody = content().json(resultBody);

		this.mockMVC.perform(updateRequestBody).andExpect(checkStatus).andExpect(checkBody);

		MvcResult result = this.mockMVC.perform(updateRequestBody).andExpect(checkStatus).andReturn();
	}

}

