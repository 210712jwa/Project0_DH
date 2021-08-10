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
	
	public Account getSpecificAccountFromClient( String stringAccountId, String stringClientId) throws ClientNotFoundException, DatabaseException {
		try {
			int accountId = Integer.parseInt(stringAccountId);
			int clientId = Integer.parseInt(stringClientId);
			
			if (clientDao.getClientById(clientId) == null) {
				throw new ClientNotFoundException(" Client with id " + stringClientId + " was not found");
			}
			
			Account accounts = accountDao.getSpecificAccountFromClient( accountId, clientId);
			
			
			return accounts;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Account addAccount(AddOrEditAccountDTO account) throws SQLException, BadParameterException, NumberFormatException {
		
		 if (account.getAccType().trim().equals("")) {
			 throw new BadParameterException("Account Type cannot be black");
		 }
		Account addedAccount = accountDao.addAccount(account);

		return addedAccount;
	}

	// edit these down here
	
	
	public Account editAccount(String stringAccountId, AddOrEditAccountDTO account) throws ClientNotFoundException, DatabaseException {
		try {
			int accId = Integer.parseInt(stringAccountId);

			if (accountDao.getAllAccountsById(accId) == null) {
				throw new ClientNotFoundException("The account with id " + accId + " was not found");
			}

			
			Account editedAccount= accountDao.editAccount(accId, account);

			return editedAccount;
		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with DAO operation");
		}
	}


	public void deleteAccount(String stringAccountId) throws DatabaseException, ClientNotFoundException, BadParameterException {
		try {
			int accId = Integer.parseInt(stringAccountId);

			List<Account> account= accountDao.getAllAccountsByClientId(accId);
			if (account == null) {
				throw new ClientNotFoundException("Account with an id " + stringAccountId + " does not exist");
			}

			clientDao.deleteClient(accId);

		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with our DAO operations");
		} catch (NumberFormatException e) {
			throw new BadParameterException( stringAccountId + " was passed in by the user as the id, " + "but it is not an int");
		}
	}

	public List<Account> getAllAccounts() throws DatabaseException {
		try {
			List<Account> account = accountDao.getAllAccounts();

			return account;
		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with DAO operation");
		}
	}

	public List<Account> getAccountsUnderCond(String stringClientId, String stringMinAmount, String stringMaxAmount) throws DatabaseException {
		try {
			int clientId = Integer.parseInt(stringClientId);
			int minAmount = Integer.parseInt(stringMinAmount);
			int maxAmount = Integer.parseInt(stringMaxAmount);
			List<Account> account = accountDao.getAccountsUnderCond(clientId, minAmount, maxAmount);

			return account;
		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with DAO operation");
		}
	}

}
