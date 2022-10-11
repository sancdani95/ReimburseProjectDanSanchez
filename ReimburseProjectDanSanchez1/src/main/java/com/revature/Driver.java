package com.revature;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.model.Person;

import io.javalin.Javalin;

public class Driver {
	
	//ctx.res().getWriter().write("Please enter your username and password.");
	
	//Person receivedPerson = ctx.bodyAsClass(Person.class);
			//System.out.println(receivedPerson);
			//ctx.status(HttpStatus.CREATED_201);
	
	public static void main(String[] args) {
		
		//starts server on 8000 port
		Javalin app = Javalin.create().start(8000);
		
		//receive username and password maybe boss to login
		app.post("/register", ctx -> {
			// check sql for matching username if not make new entry in db
			ctx.status(HttpStatus.CREATED_201);
		})
		
		//receives login request
		app.post("/login", ctx -> {
			Person receivedPerson = ctx.bodyAsClass(Person.class);
			if (receivedPerson.getUsername() == ){ //SQL check here)
				// get userid and then use it to get corrisponding password
				if (receivedPerson.getPassword() == ) {//check SQL password
					//send approved login message
				}
				else //send password does not match message
					
		}
			else // send username does not match
			
		});
		
		
		//request manager and employee past tickets from SQl databace
		app.get("/employee", ctx -> {	
			if ();
		// recall sql script where username matched
		});
		
		//Submit new ticket
		app.post("/new_ticket", ctx -> {
			
		});
	
	
	
	
	
	
	
	
	}

}
