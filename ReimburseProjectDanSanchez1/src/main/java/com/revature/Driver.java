package com.revature;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.http.HttpStatus;


import com.revature.model.*;
import com.revature.repository.*;

import io.javalin.Javalin;

public class Driver {
	
	//ctx.res().getWriter().write("Please enter your username and password.");
	
	//Person receivedPerson = ctx.bodyAsClass(Person.class);
			//System.out.println(receivedPerson);
			//ctx.status(HttpStatus.CREATED_201);
	
	public static void main(String[] args) {
		
		
		//starts server on 8000 port
		Javalin app = Javalin.create().start(8000);
		
		
		
		//recieves login information
		app.get("/login", ctx -> {
			
			List<String> users = new ArrayList<>();
			users = PersonRepository.checkUser();
			
			Person receivedUser = ctx.bodyAsClass(Person.class);
			Person pullUser = PersonRepository.pullUser(receivedUser);
			String name = receivedUser.getPerson_username();
			String rPass = receivedUser.getPerson_password();
			String pPass = pullUser.getPerson_password();
			
			if (users.contains(name)) {
				if (rPass == pPass) {
					ctx.json("Welcome User");
				}
				
			} else {
			ctx.json("User does not Exist.");
			}
			
			
		});
		
	
		
		//receive username and password maybe boss to login
		app.post("/register", ctx -> {
			
			Person receivedPerson = new Person();
			receivedPerson = ctx.bodyAsClass(Person.class);
			
			PersonRepository.save(receivedPerson);
			
			if (receivedPerson.isPerson_boss()) {
				ctx.result("New Manager Created!");
			} else {
				ctx.result("New Employee Created!");
			}
			ctx.status(HttpStatus.CREATED_201);
			
		});
		
		
		
		//request manager and employee past tickets from SQl database
		app.get("/ticket", ctx -> {	
		
			//save sent information
			Person receivedID = ctx.bodyAsClass(Person.class);
			
			//create list to store results
			List<Ticket> ticketList = new ArrayList<>();
			
			//runs the find ticket method
			ticketList = TicketRepository.findTicket(receivedID);
	
			if (ticketList.size() == 0) {
				ctx.result("No tickets submitted");
			}else {
				ctx.json(ticketList);
			}
			
		});
		
		
		
		//Submit new ticket
		app.post("/new_ticket", ctx -> {
			
			Ticket recieveTicket = new Ticket();
			recieveTicket = ctx.bodyAsClass(Ticket.class);
			
			TicketRepository.submit(recieveTicket);
			
			ctx.result("New Ticket Submitted!");
			ctx.status(HttpStatus.CREATED_201);
			
		});
	
	
	
		//manager Approves or denies tickets - need ticket_id, approve or deny
		app.post("/ticket_respond", ctx ->{
			
			Ticket recievedTicket = ctx.bodyAsClass(Ticket.class);
			
			TicketRepository.respond(recievedTicket);
			
			ctx.result("Ticket Updated");
			ctx.status(HttpStatus.ACCEPTED_202);
		});
	
	
	
	}
	
	

}
