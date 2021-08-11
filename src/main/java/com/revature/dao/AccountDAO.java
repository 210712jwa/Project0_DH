package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.dto.AddOrEditAccountDTO;
import com.revature.dto.EditAccountDTO;
import com.revature.model.Account;
import com.revature.model.Client;

/**
 * * methods here returns a account(s) back DTOs are classes that are used to
 * pass data around that might not completely conform to the actual "Model"
 * class
 * 
 * @author david
 *
 */

public interface AccountDAO {

	List<Account> getAllAccounts() throws SQLException;

	Account addAccount(AddOrEditAccountDTO account) throws SQLException;

	Account editAccount(int clientId, AddOrEditAccountDTO account) throws SQLException;
	
	void deleteAccount(int clientId, int accId) throws SQLException;

	Account getSpecificAccountFromClient(int accountId, int clientId) throws SQLException;

	List<Account> getAccountsWithMinMaxCond(int clientId, double maxAmount, double minAmount) throws SQLException;

	// method overloading
	List<Account> getAllAccountsByClientId(int clientId) throws SQLException;

	List<Account> getAllAccountsByClientId(int clientId, double minAmount, double maxAmount) throws SQLException;

}
