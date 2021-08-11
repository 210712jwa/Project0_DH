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

import ch.qos.logback.core.net.SyslogOutputStream;

import com.revature.exception.BadParameterException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.DatabaseException;

//handles all the exceptions here and performs DAOimpl methods if conditions are met
public class AccountService {

	private AccountDAO accountDao;
	private ClientDAO clientDao;

	// instantiate REAL AccountDAO object
	public AccountService() {
		this.accountDao = new AccountDAOImpl();
		this.clientDao = new ClientDAOImpl();
	}

	public List<Account> getAccountsFromClient(String stringClientId, String stringMaxAmount, String stringMinAmount)
			throws BadParameterException, DatabaseException, ClientNotFoundException, SQLException {

		int clientId = Integer.parseInt(stringClientId);

		if (clientDao.getClientById(clientId) == null) {

			throw new ClientNotFoundException(" Client with id " + stringClientId + " was not found");
		}
		if (stringMinAmount != null && stringMaxAmount != null) {

			double minAmount = Double.parseDouble(stringMinAmount);
			double maxAmount = Double.parseDouble(stringMaxAmount);

			List<Account> accounts = accountDao.getAccountsWithMinMaxCond(clientId, maxAmount, minAmount);

			return accounts;
		} else {

			List<Account> accounts = accountDao.getAllAccountsByClientId(clientId);
			return accounts;
		}
	}

	public Account getSpecificAccountFromClient(String stringAccountId, String stringClientId)
			throws ClientNotFoundException, DatabaseException {
		try {
			int accountId = Integer.parseInt(stringAccountId);
			int clientId = Integer.parseInt(stringClientId);

			if (clientDao.getClientById(clientId) == null) {
				throw new ClientNotFoundException(" Client with id " + stringClientId + " was not found");
			}

			Account accounts = accountDao.getSpecificAccountFromClient(accountId, clientId);

			return accounts;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Account addAccount(AddOrEditAccountDTO account)
			throws SQLException, BadParameterException, NumberFormatException {

		if (account.getAccType().trim().equals("")) {
			throw new BadParameterException("Account Type cannot be black");
		}
		Account addedAccount = accountDao.addAccount(account);

		return addedAccount;
	}

	// edit these down here

	public Account editAccount(String stringClientId, String stringAccountId, AddOrEditAccountDTO account) throws ClientNotFoundException, DatabaseException {
		try {
			int clientId = Integer.parseInt(stringClientId);
			int accId = Integer.parseInt(stringAccountId);

			if (accountDao.getAllAccountsByClientId(accId) == null) {
				throw new ClientNotFoundException("The account with id " + accId + " was not found");
			}

			Account editedAccount = accountDao.editAccount(accId, account);

			return editedAccount;
		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with DAO operation");
		}
	}

	public void deleteAccount(String stringClientId, String stringAccountId)
			throws DatabaseException, ClientNotFoundException, BadParameterException {
		try {
			int accId = Integer.parseInt(stringClientId);
			int clientId = Integer.parseInt(stringAccountId);
			Client client = clientDao.getClientById(clientId);
			if (client == null) {
				throw new ClientNotFoundException("Account with an id " + stringAccountId + " does not exist");
			}

			accountDao.deleteAccount(clientId, accId);

		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with our DAO operations");
		} catch (NumberFormatException e) {
			throw new BadParameterException(
					stringAccountId + " was passed in by the user as the id, " + "but it is not an int");
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

//	public List<Account> getAllAccountsFromClientWithMinCond(String stringClientId, String stringMinAmount) throws DatabaseException, SQLException {
//		int clientId = Integer.parseInt(stringClientId);
//		double minAmount = Double.parseDouble(stringMinAmount);
//		
//		List<Account> account = accountDao.getAccountsWithMinCond(clientId, minAmount);
//		
//		return account;
//	}
//
//	public List<Account> getAllAccountsFromClientWithMaxCond(String stringClientId, String stringMinAmount) throws SQLException {
//		int clientId = Integer.parseInt(stringClientId);
//		double maxAmount = Double.parseDouble(stringMinAmount);
//		
//		List<Account> account = accountDao.getAccountsWithMaxCond(clientId, maxAmount);
//		
//		return account;
//	}
//
//	public List<Account> getAllAccountsFromClientWithMinMaxCond(String stringClientId, String stringMinAmount, String stringMaxAmount) throws DatabaseException {
//		try {
//			int clientId = Integer.parseInt(stringClientId);
//			double minAmount = Double.parseDouble(stringMinAmount);
//			double maxAmount = Double.parseDouble(stringMaxAmount);
//
//			List<Account> account = accountDao.getAccountsWithMinMax(clientId, minAmount, maxAmount);
//
//			return account;
//		} catch (SQLException e) {
//			throw new DatabaseException("Something went wrong with DAO operation");
//		}
//	}

}
