package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.dto.AddOrEditAccountDTO;
import com.revature.dto.EditAccountDTO;
import com.revature.model.Account;
import com.revature.model.Client;


public interface AccountDAO {

	List<Account> getAllAccounts() throws SQLException;
	

void deleteAccount(int clientId) throws SQLException;

	//Account addAccount(int clientId) throws SQLException;

	Account addAccount(AddOrEditAccountDTO account) throws SQLException;

	Account editAccount(int clientId, AddOrEditAccountDTO account) throws SQLException;


	List<Account> getAllAccountsByClientId(int clientId) throws SQLException;

	Account getSpecificAccountFromClient(int accountId, int clientId) throws SQLException;


	List<Account> getAccountsUnderCond(int accId, int minAmount, int maxAmount) throws SQLException;


	List<Account> getAllAccountsById(int clientId) throws SQLException;


	

}
