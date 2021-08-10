package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.ClientDAO;
import com.revature.dao.ClientDAOImpl;
import com.revature.dto.AddOrEditAccountDTO;
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

	public List<Account> getAllAccountsFromClient(String stringclientId)throws BadParameterException, DatabaseException, ClientNotFoundException {

		try {
			int clientId = Integer.parseInt(stringclientId);
			
			List<Account> accounts = accountDao.getAllAccountsById(clientId);

			if (clientDao.getClientById(clientId) == null) {
				throw new ClientNotFoundException(" Client with id " + stringclientId + " was not found");
			}
			return accounts;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Account addAccount(AddOrEditAccountDTO account) throws SQLException, BadParameterException, NumberFormatException {
		
		int clientId = Integer.parseInt(account.getClientId());
		
		
		 if (account.getAccType().trim().equals("")) {
			 throw new BadParameterException("Account Type cannot be black");
		 }
		Account addedAccount = accountDao.addAccount(account);

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
