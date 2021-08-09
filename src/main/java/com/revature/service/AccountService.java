package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.ClientDAO;
import com.revature.dao.ClientDAOImpl;
import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.exception.BadParameterException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.DatabaseException;

public class AccountService {

	private AccountDAO accountDao;
	private ClientDAO clientDao;

	public AccountService() {
		this.accountDao = new AccountDAOImpl();
		this.clientDao = new ClientDAOImpl();
	}

	public List<Account> getAllAccountsFromClient(String stringClientId)throws BadParameterException, ClientNotFoundException, DatabaseException {

		try {
			int clientId = Integer.parseInt(stringClientId);
			
			List<Account> accounts = accountDao.getAllAccountsById(clientId);

			if (clientDao.getClientById(clientId) == null) {
				throw new ClientNotFoundException(" Client with id " + stringClientId + " was not found");
			}

			return accounts;

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} catch (ClientNotFoundException e) {
			throw new BadParameterException(stringClientId + " was passed in by the user as the id, " + "but it is not an int");
		}
	}

	public List<Account> addAccountToClient(String stringClientId) {
		int clientId = Integer.parseInt(stringClientId);
		
		List<Account> addedAccount = accountDao.addAccount(clientId);

		return addedAccount;
	}

	
	
	
	
	
	

	public List<Account> editAccount(String stringClientId) {
		int clientId = Integer.parseInt(stringClientId);
		return null;
	}

	public List<Account> delete(String stringClientId) {
		int clientId = Integer.parseInt(stringClientId);
		return null;
	}
}
