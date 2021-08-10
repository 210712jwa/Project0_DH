package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.dto.AddOrEditAccountDTO;
import com.revature.dto.EditAccountDTO;
import com.revature.model.Account;
import com.revature.model.Client;


public interface AccountDAO {

	List<Account> getAllAccounts(int clientId) throws SQLException;

	List<Account> getAllAccountsById(int clientId) throws SQLException;

	
	Account editAccount(int clientId);

	Account deleteAccount(int clientId);

	Account addAccount(AddOrEditAccountDTO account) throws SQLException;
	

}
