package com.revature.controller;

import java.util.List;

import com.revature.dto.AddOrEditAccountDTO;
import com.revature.dto.AddOrEditClientDTO;
import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.service.AccountService;
import com.revature.service.ClientService;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
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
		String maxAmount = ctx.queryParam("amountLessThan");
		String minAmount = ctx.queryParam("amountGreaterThan");


		
			List<Account> accounts = AccountService.getAccountsFromClient(clientId, maxAmount, minAmount);
		
	ctx.status(200);
	ctx.json(accounts);
	};
	
	private Handler getSpecificAccountFromClient = (ctx) -> {
		String clientId = ctx.pathParam("clientId");
		String accountId = ctx.pathParam("accountId");
		
	Account accounts = AccountService.getSpecificAccountFromClient( accountId, clientId);
	
	ctx.status(200);
	ctx.json(accounts);
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
		String clientId = ctx.pathParam("clientId");
		String accountId = ctx.pathParam("accountId");
		
	AccountService.deleteAccount(clientId, accountId);
	
	};

	
	public void mapEndpoints(Javalin app){
		app.get("/account", getAllAccounts);
		app.get("/client/:clientId/account", getAccountsFromClient);
		app.get("/client/:clientId/account/:accountId", getSpecificAccountFromClient);
		app.post("/client/:clientId/account", addAccount);
		app.put("/client/:clientId/account", editAccount);
		app.delete("/client/:clientId/account/:accountId", deleteAccount); // make a handler for each one

	}
}
