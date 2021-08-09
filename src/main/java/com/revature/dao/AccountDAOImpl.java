package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.util.ConnectionUtility;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public List<Account> getAllAccounts(int clientId) throws SQLException {
		try (Connection con = ConnectionUtility.getConnection()) {

			Statement stmt = con.createStatement();

			String sql = "SELECT * FROM Project0.account WHERE client.id = ?";
			stmt.executeQuery(sql);
			ResultSet rs = stmt.executeQuery(sql); // this resultset sends back result set from query

			while (rs.next()) { // iterate cursor until no more rows.
				int cId = rs.getInt("id"); // returns true for forward,F for no more rows
				String accType = rs.getString("type");
				int accNumber = rs.getInt("accNumber");

				return null;
			}
		}
		return null;
	}

	@Override
	public List<Account> getAllAccountsById(int clientId) throws SQLException {


		try (Connection con = ConnectionUtility.getConnection()) {
			List<Account> accounts = new ArrayList<>();
			
			String sql = "SELECT * FROM Project0.account a WHERE a.clientId = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, clientId);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int client_id = rs.getInt("clientId");
				int acc_id = rs.getInt("accId");
				String accType = rs.getString("accType");
				int balance = rs.getInt("balance");


				Account a = new Account(client_id, acc_id, accType, balance);
				accounts.add(a);
			} 
			return accounts;
		}
	}

	@Override
	public List<Account> addAccount(int clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> editAccount(int clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> deleteAccount(int clientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
