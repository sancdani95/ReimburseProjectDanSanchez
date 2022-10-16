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
			ctx.res().getWriter().write("Please enter your username and password.");
		});
		
		
		//receive username and password maybe boss to login
		app.post("/register", ctx -> {
			
			Person receivedPerson = new Person();
			
			receivedPerson = ctx.bodyAsClass(Person.class);
			PersonRepository.save(receivedPerson);
			
			System.out.println(receivedPerson);
			ctx.status(HttpStatus.CREATED_201);
			
		});
		
		
		
		//request manager and employee past tickets from SQl database
		app.get("/ticket", ctx -> {	
		
			//save sent information
			Person receivedID = new Person();
			receivedID = ctx.bodyAsClass(Person.class);
			List<Ticket> ticketList = new ArrayList<>();
			
			ticketList = PersonRepository.findTicket(receivedID);
	
			System.out.println(ticketList);
		});
		
		
		//Submit new ticket
		app.post("/new_ticket", ctx -> {
			
		});
	
	
	
		//manager Approves or denies tickets - need ticket_id, approve or deny
		app.post("/ticket_responce", cxt ->{
			
		});
	
	
	
	}
	
	

}
