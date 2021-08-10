package com.revature.controller;

import java.util.List;

import com.revature.dto.AddOrEditAccountDTO;
import com.revature.dto.AddOrEditClientDTO;
import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.service.AccountService;
import com.revature.service.ClientService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AccountController implements Controller {
	private AccountService AccountService;
	
	public AccountController() {
		this.AccountService = new AccountService();
	}
	
	private Handler getAccountsFromClient = (ctx) -> {
		String clientId = ctx.pathParam("clientId");
		
	List<Account> clients = AccountService.getAllAccountsFromClient(clientId);
	
	ctx.status(200);
	ctx.json(clients);
	};
	
	private Handler addAccount = (ctx) -> {
		AddOrEditAccountDTO accountToAdd = ctx.bodyAsClass(AddOrEditAccountDTO.class);

		Account addedAccount = AccountService.addAccount(accountToAdd);
	
	
	ctx.status(200);
	ctx.json(addedAccount);
	};
	
	private Handler editAccount = (ctx) -> {
		String clientId = ctx.pathParam("clientId");
		
	List<Account> clients = AccountService.editAccount(clientId);
	
	ctx.status(200);
	ctx.json(clients);
	};
	
	private Handler deleteAccount = (ctx) -> {
		String clientId = ctx.pathParam("clientId");
		
	List<Account> clients = AccountService.delete(clientId);
	
	ctx.status(200);
	ctx.json(clients);
	};

//		"accountType": "checking",
//		"balance" : 134324
//		}

	
	public void mapEndpoints(Javalin app) {
		app.get("/client/:clientId/account", getAccountsFromClient);
		app.post("/client/:clientId/account", addAccount);
		app.put("/client/:clientId/account", editAccount);
		app.delete("/client/:clientId/account", deleteAccount);		// make a handler for each one
		
		
	}
}
