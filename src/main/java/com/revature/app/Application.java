package com.revature.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.AccountController;
import com.revature.controller.ClientController;
import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;

import io.javalin.Javalin;

public class Application {

	private static Javalin app;
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	
	public static void main(String[] args) {
		app = Javalin.create();
		
		mapControllers( new ClientController(), new AccountController(), new ExceptionController());
		
		app.start(7000);

		

	}
	
	public static void mapControllers(Controller... controllers ) { // using var-args
		for (Controller c : controllers) {	// abstraction and polymorphism
			c.mapEndpoints(Application.app);
		}
	}

}
