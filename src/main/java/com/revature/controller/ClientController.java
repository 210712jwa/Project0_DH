package com.revature.controller;

import java.util.List;

import com.revature.dto.AddOrEditClientDTO;
import com.revature.model.Client;
import com.revature.service.ClientService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
/**
 * collect HTTP input for clients and then calls a Handler
 * @author david
 *
 */
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

	private Handler getClientById = (ctx) -> {
		String clientid = ctx.pathParam("clientid");

		Client client = ClientService.getClientById(clientid);

		ctx.json(client);
	};

	private Handler addClient = (ctx) -> {
		AddOrEditClientDTO shipToAdd = ctx.bodyAsClass(AddOrEditClientDTO.class);

		Client addedClient = ClientService.addClient(shipToAdd);
		ctx.json(addedClient);
	};

	private Handler editClient = (ctx) -> {
		AddOrEditClientDTO clientToEdit = ctx.bodyAsClass(AddOrEditClientDTO.class);

		String clientId = ctx.pathParam("clientid");
		Client editedClient = ClientService.editClient(clientId, clientToEdit);

		ctx.json(editedClient);
	};

	private Handler deleteClient = (ctx) -> {
		String clientId = ctx.pathParam("clientid");

		ClientService.deleteClient(clientId);
	};
	

	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/client", getAllClients);
		app.get("/client/:clientid", getClientById);
		app.post("/client", addClient);
		app.put("/client/:clientid", editClient);
		app.delete("/client/:clientid", deleteClient);

	}

}
