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
	
	private Handler getAllAccounts = (ctx) -> {
		
	List<Account> accounts = AccountService.getAllAccounts();
	
	ctx.status(200);
	ctx.json(accounts);
	};
	
	private Handler getAccountsFromClient = (ctx) -> {
		String clientId = ctx.pathParam("clientId");
		
	List<Account> clients = AccountService.getAllAccountsFromClient(clientId);
	
	ctx.status(200);
	ctx.json(clients);
	};
	
	private Handler getSpecificAccountFromClient = (ctx) -> {
		String clientId = ctx.pathParam("clientId");
		String accountId = ctx.pathParam("accountId");
		
	Account clients = AccountService.getSpecificAccountFromClient( accountId, clientId);
	
	ctx.status(200);
	ctx.json(clients);
	};
	
	private Handler getAccountUnderCond = (ctx) -> {
		String clientId = ctx.pathParam("clientId");
		String accountId = ctx.pathParam("accountId");
		String minAmount = ctx.pathParam("amountLessThan");
		String maxAmount = ctx.pathParam("accountGreaterThan");
		
		
		if (ctx.queryParam("amountLessThan") != null && ctx.queryParam("amountGreaterThan") != null) {
			
			List<Account> accounts = AccountService.getAccountsUnderCond(clientId, minAmount, maxAmount);
			
		}
		
	Account clients = AccountService.getSpecificAccountFromClient(clientId, accountId);
	
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
		AddOrEditAccountDTO accountToEdit = ctx.bodyAsClass(AddOrEditAccountDTO.class);
		
		String accountId = ctx.pathParam("accId");
		Account editedAccount = AccountService.editAccount(accountId, accountToEdit);
	
	ctx.status(200);
	ctx.json(editedAccount);
	};
	
	private Handler deleteAccount = (ctx) -> {
		String accountId = ctx.pathParam("accountId");
		
	AccountService.deleteAccount(accountId);
	

	};

//		"accountType": "checking",
//		"balance" : 134324
//		}

	
	public void mapEndpoints(Javalin app) {
		app.get("/account", getAllAccounts);
		app.get("/client/:clientId/account", getAccountsFromClient);
		app.get("/client/:clientId/account/:accountId", getSpecificAccountFromClient);
		app.get("/clients/:clientId/account/:amountLessThan/:amountGreaterThan" , getAccountUnderCond);
		app.post("/client/:clientId/account", addAccount);
		app.put("/client/:clientId/account", editAccount);
		app.delete("/client/:clientId/:accountId", deleteAccount);		// make a handler for each one
		
		
	}
}
