package com.revature;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.http.HttpStatus;


import com.revature.model.*;
import com.revature.repository.*;

import io.javalin.Javalin;
import jakarta.servlet.http.Cookie;



public class Driver {
	
	//ctx.res().getWriter().write("Please enter your username and password.");
	
	//Person receivedPerson = ctx.bodyAsClass(Person.class);
			//System.out.println(receivedPerson);
			//ctx.status(HttpStatus.CREATED_201);
	
	public static void main(String[] args) {
		
		
		//starts server on 8000 port
		Javalin app = Javalin.create().start(8000);
		
		app.post("/test", ctx -> {
			ctx.json("working");
		});
		
		
		//receives login information
		app.post("/login", ctx -> {
			
			//creates variables for login procedure
			List<String> users = new ArrayList<>();
			users = PersonRepository.checkUser();
			
			
			Person receivedUser = ctx.bodyAsClass(Person.class);
			String name = receivedUser.getPerson_username();
			
			if (users.contains(name)) {
				Person pullUser = PersonRepository.pullUser(receivedUser);
				String pPass = pullUser.getPerson_password();
				String rPass = receivedUser.getPerson_password();
				
				
				if (rPass.equals(pPass)) {
					ctx.res().addCookie(CookieRepository.aCookie());
					ctx.res().addCookie(CookieRepository.uCookie(name));
					
					if(pullUser.isPerson_boss()) {
						ctx.res().addCookie(CookieRepository.mCookie());
						ctx.json("Welcome manager " + name);
						
					} else {
						ctx.res().addCookie(CookieRepository.eCookie());
						ctx.json("Welcome employee " + name);
						
					}
					
				} else {
					ctx.json("Password is Incorrect.");
					ctx.status(HttpStatus.BAD_REQUEST_400);
				}
			
			} else {
				ctx.json("Username does not Exist.");
				ctx.status(HttpStatus.BAD_REQUEST_400);
			}
			
		});
		
	
		
		//receive username and password maybe boss to login
		app.post("/register", ctx -> {
			
			Person receivedPerson = new Person();
			receivedPerson = ctx.bodyAsClass(Person.class);
			String rUser = receivedPerson.getPerson_username();
			String rPass = receivedPerson.getPerson_password();
			
			
			if (rUser.isBlank() || rPass.isBlank()) {
				ctx.json("please provide a username and password");
				ctx.status(HttpStatus.BAD_REQUEST_400);
				
			}	else {
				
			
			PersonRepository.save(receivedPerson);
			
			if (receivedPerson.isPerson_boss()) {
				ctx.result("New Manager Created!");
			} else {
				ctx.result("New Employee Created!");
			}
			ctx.status(HttpStatus.CREATED_201);
			
			}
			
		});
		
		
		
		//request manager and employee past tickets from SQl database
		app.get("/ticket", ctx -> {	
		
			Cookie[] rCookies = ctx.req().getCookies();
			boolean authenticated = CookieRepository.checkAuth(rCookies);
			
			
			if (authenticated) {
				
				Person receivedID = new Person(1, CookieRepository.getUser(rCookies), "text", CookieRepository.getRole(rCookies));
				
			
			//create  list to store results
			List<Ticket> ticketList = new ArrayList<>();
			
			//runs the find ticket method
			ticketList = TicketRepository.findTicket(receivedID);
	
			if (ticketList.size() == 0) {
				ctx.result("No tickets submitted");
			}else {
				ctx.json(ticketList);
			}
			
			} else {
				ctx.json("please login");
				ctx.status(HttpStatus.LOCKED_423);
			}
			
		});
		
		
		
		//Submit new ticket
		app.post("/new_ticket", ctx -> {
			
			Cookie[] rCookies = ctx.req().getCookies();
			boolean authenticated = CookieRepository.checkAuth(rCookies);
			
			
			if (authenticated) {
				
			Person receivedID = new Person(1, CookieRepository.getUser(rCookies), "text", CookieRepository.getRole(rCookies));
				
			Ticket recieveTicket = ctx.bodyAsClass(Ticket.class);
			
			if (recieveTicket.getTicket_amount() == 0) {
				ctx.json("needs to have an amount");
				ctx.status(HttpStatus.BAD_REQUEST_400);
			
			} else {
			
					if (recieveTicket.getTicket_description().isBlank()) {
					ctx.json("needs to have a description");
				} else {
				
			TicketRepository.submit(recieveTicket, receivedID);
			
			ctx.result("New Ticket Submitted!");
			ctx.status(HttpStatus.CREATED_201);
			}
			}
			
			} else {
				ctx.json("please login");
				ctx.status(HttpStatus.LOCKED_423);
			}
			
			
		});
	
	
	
		//manager Approves or denies tickets - need ticket_id, approve or deny
		app.post("/ticket_respond", ctx ->{
			
			Cookie[] rCookies = ctx.req().getCookies();
			boolean authenticated = CookieRepository.checkAuth(rCookies);
			
			
			if (authenticated) {
				
				if (CookieRepository.getRole(rCookies)) {
			
			Ticket recievedTicket = ctx.bodyAsClass(Ticket.class);
			
			String status = TicketRepository.rStatus(recievedTicket);
			
			if (status.equals("pending")) {
			
			TicketRepository.respond(recievedTicket);
			
			ctx.result("Ticket Updated");
			ctx.status(HttpStatus.ACCEPTED_202);
			} else {
				ctx.json("this ticket has all ready been " + status);
				ctx.status(HttpStatus.BAD_REQUEST_400);
			}
			
				} else {
					ctx.json("you do not have access");
					ctx.status(HttpStatus.BAD_REQUEST_400);
				
				}
			
			} else {
				ctx.json("please login");
				ctx.status(HttpStatus.LOCKED_423);
			}
			
		});
	
	
		app.get("/logout", ctx -> {

			Cookie[] cookies = ctx.req().getCookies();
			
			for (Cookie cookie : cookies) {
			//	if (cookie.getName().equals("authenticated")) cookie.setMaxValue(0);
				if (cookie.getName().equals("auth")) {
					ctx.res().addCookie(CookieRepository.laCookie()); //had to force update cookies for postman
					ctx.res().addCookie(CookieRepository.lrCookie());
					ctx.res().addCookie(CookieRepository.luCookie());
				}
				
			}
			ctx.json("You have been Logged out.");
		});
	
	}
	
	

}
