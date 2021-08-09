package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Account;
import com.revature.model.Client;


public interface AccountDAO {

	List<Account> getAllAccounts(int clientId) throws SQLException;

	List<Account> getAllAccountsById(int clientId) throws SQLException;

	List<Account> addAccount(int clientId);
	
	List<Account> editAccount(int clientId);

	List<Account> deleteAccount(int clientId);
	

}
