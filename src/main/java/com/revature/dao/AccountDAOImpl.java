package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.AddOrEditAccountDTO;
import com.revature.dto.EditAccountDTO;
import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.util.ConnectionUtility;
//if (ctx.queryParam("amountLessThan") != null && ctx.queryParam("amountGreaterThan") != null) {}


public class AccountDAOImpl implements AccountDAO {

	private AccountDAO accountDao;

	@Override
	public List<Account> getAllAccounts() throws SQLException {
		List<Account> accounts = new ArrayList<>();

		try (Connection con = ConnectionUtility.getConnection()) {

			Statement stmt = con.createStatement();

			String sql = "SELECT * FROM Project0.account";
			stmt.executeQuery(sql);
			ResultSet rs = stmt.executeQuery(sql); // this resultset sends back result set from query

			while (rs.next()) { // iterate cursor until no more rows.
				int accId = rs.getInt("accId"); // returns true for forward,F for no more rows
				int clientId = rs.getInt("clientId");
				String accType = rs.getString("accType");
				int balance = rs.getInt("balance");

				Account account = new Account(accId, clientId, accType, balance);
				accounts.add(account);

			}
		}
		return accounts;
	}



	@Override
	public Account getSpecificAccountFromClient(int accountId, int clientId) throws SQLException {
		try (Connection con = ConnectionUtility.getConnection()) {

			String sql = "SELECT * FROM Project0.account p WHERE p.accId = ? AND p.clientId = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
		
			pstmt.setInt(1, accountId);
			pstmt.setInt(2, clientId);
			ResultSet rs = pstmt.executeQuery(); // this resultset sends back result set from query
			if (rs.next()) { // iterate cursor until no more rows.
				int acc_id = rs.getInt("accId");
				int client_id = rs.getInt("clientId");
				String accType = rs.getString("accType");
				double balance = rs.getDouble("balance");

				Account acc = new Account(acc_id, client_id, accType, balance);
				return acc;
			} else {
				return null;
			}
		}

	}

	@Override

	public List<Account> getAllAccountsByClientId(int clientId) throws SQLException {

		try (Connection con = ConnectionUtility.getConnection()) {
			List<Account> accounts = new ArrayList<>();
			String sql = "SELECT * FROM Project0.account a WHERE a.clientId = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, clientId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int client_id = rs.getInt("clientId");
				int acc_id = rs.getInt("accId");
				String accType = rs.getString("accType");
				double balance = rs.getDouble("balance");

				Account acc = new Account(acc_id, client_id, accType, balance);
				accounts.add(acc);

			}
			return accounts;
		}
	}
	
	@Override
	public List<Account> getAccountsWithMinMaxCond(int clientId, double maxAmount, double minAmount) throws SQLException {
		try (Connection con = ConnectionUtility.getConnection()) {
			
			List<Account> accounts = new ArrayList<>();
			String sql = "SELECT * FROM Project0.account a WHERE a.clientId = ? AND a.balance < ? AND a.balance > ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, clientId);
			pstmt.setDouble(2, maxAmount);
			pstmt.setDouble(3, minAmount);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int client_id = rs.getInt("clientId");
				int acc_id = rs.getInt("accId");
				String accType = rs.getString("accType");
				double balance = rs.getDouble("balance");

				Account acc = new Account(acc_id, client_id, accType, balance);
				accounts.add(acc);

			}
			return accounts;
		}
	}
	

	@Override
	public Account addAccount(AddOrEditAccountDTO account) throws SQLException {
		try (Connection con = ConnectionUtility.getConnection()) {
			String sql = "INSERT INTO Project0.account ( clientId, accType, balance) VALUES (?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, account.getClientId());
			pstmt.setString(2, account.getAccType());
			pstmt.setDouble(3, account.getBalance());

			int recordsUpdated = pstmt.executeUpdate();

			if (recordsUpdated != 1) {
				throw new SQLException("Could not insert new Account");
			}
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {

				Account createdAccount = new Account(generatedKeys.getInt(1), account.getClientId(),
						account.getAccType(), account.getdBalance());

				return createdAccount;

			} else {
				throw new SQLException("Autogenerated id could not be generated for client");
			}
		}
	}

	@Override
	public Account editAccount(int acctId, AddOrEditAccountDTO account) throws SQLException {
		try (Connection con = ConnectionUtility.getConnection()) {
			String sql = "UPDATE Project0.account SET balance WHERE accId = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setDouble(1, account.getBalance());

			int recordUpdate = pstmt.executeUpdate(); // recordUpdate returns a int

			if (recordUpdate != 1) {
				throw new SQLException(" Record was not able to be updated");
			}

		}
		return new Account();
	}


	
	@Override
	public List<Account> getAllAccountsByClientId(int clientId, double minAmount, double maxAmount) throws SQLException {
		try (Connection con = ConnectionUtility.getConnection()) {
			List<Account> accounts = new ArrayList<>();

			String sql = "SELECT * FROM Project0.account a WHERE a.clientId = ? && balance > ? && balance < ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, clientId);
			pstmt.setDouble(2, minAmount);
			pstmt.setDouble(3, maxAmount);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int client_id = rs.getInt("clientId");
				int acc_id = rs.getInt("accId");
				String accType = rs.getString("accType");
				double balance = rs.getDouble("balance");

				Account acc = new Account(acc_id, client_id, accType, balance);
				accounts.add(acc);
			}
		return accounts;
		}
	}	

	@Override
	public void deleteAccount( int accId, int clientId) throws SQLException {
		try (Connection con = ConnectionUtility.getConnection()) {
			String sql = "DELETE FROM Project0.account WHERE clientId = ? AND accId = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, clientId);
			pstmt.setInt(2, accId);

			System.out.println(clientId);
			System.out.println(accId);
			int recordsDeleted = pstmt.executeUpdate();

			// if it is not 1, we know that no records were actually deleted
			if (recordsDeleted != 1) {
				throw new SQLException("Record was not able to be deleted");
			}
		}
		
	}
}



//	@Override
//	public List<Account> getAccountsWithMinMaxCond(int clientId, double minAmount, double MaxAmount) throws SQLException {
//		List<Account> accounts = new ArrayList<>();
//		try (Connection con = ConnectionUtility.getConnection()) {
//
//			String sql = "SELECT * FROM Project0.account a WHERE a.clientId = ? && balance > ? && balance < ?";
//			PreparedStatement pstmt = con.prepareStatement(sql);
//
//			pstmt.setInt(1, clientId);
//			pstmt.setDouble(2, minAmount);
//
//			ResultSet rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				int client_id = rs.getInt("clientId");
//				int acc_id = rs.getInt("accId");
//				String accType = rs.getString("accType");
//				double balance = rs.getDouble("balance");
//
//				Account acc = new Account(acc_id, client_id, accType, balance);
//				accounts.add(acc);
//			}
//	}	return accounts;
//	}
//}

//	@Override
//	public List<Account> getAccountsWithMaxCond(int clientId, double maxAmount) throws SQLException {
//		List<Account> accounts = new ArrayList<>();
//		try (Connection con = ConnectionUtility.getConnection()) {
//
//			String sql = "SELECT * FROM Project0.account a WHERE a.clientId = ? && balance > ? && balance < ?";
//			PreparedStatement pstmt = con.prepareStatement(sql);
//
//			pstmt.setInt(1, clientId);
//			pstmt.setDouble(3, maxAmount);
//
//			ResultSet rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				int client_id = rs.getInt("clientId");
//				int acc_id = rs.getInt("accId");
//				String accType = rs.getString("accType");
//				double balance = rs.getDouble("balance");
//
//				Account acc = new Account(acc_id, client_id, accType, balance);
//				accounts.add(acc);
//			}
//	}	return accounts;
//	}






