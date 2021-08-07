package com.revature.controller;


import java.util.List;

import com.revature.model.Client;
import com.revature.service.ClientService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ClientController implements Controller {

	private ClientService ClientService;
	
	public ClientController() {
		this.ClientService = new ClientService();
	}
	
	private Handler getAllClients = (ctx) -> {
	List<Client> clients = ClientService.getAllClients();
	
	ctx.status(200);
	ctx.json(clients);
	};
	
	private Handler getClientById  = (ctx) -> {
		String clientid = ctx.pathParam("clientid");
		
		Client client = ClientService.getClientById(clientid);
		
		ctx.json(client);
		};
	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/client", getAllClients);
		app.get("/client/:clientId", getClientById);
		
		
	}

}
