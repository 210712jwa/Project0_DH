package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.AddOrEditClientDTO;
import com.revature.model.Client;
import com.revature.util.ConnectionUtility;

public class ClientDAOImpl implements ClientDAO {

	@Override
	public List<Client> getAllClients() throws SQLException {

		List<Client> clients = new ArrayList<>();

		try (Connection con = ConnectionUtility.getConnection()) { // try w\ resource: no need to handle exception, auto
																	// close for us

			Statement stmt = con.createStatement();

			String sql = "SELECT * FROM Project0.client";
			stmt.executeQuery(sql);
			ResultSet rs = stmt.executeQuery(sql); // this resultset sends back result set from query

			while (rs.next()) { // iterate cursor until no more rows.
				int id = rs.getInt("id"); // returns true for forward,F for no more rows
				String name = rs.getString("name");

				Client client = new Client(id, name);

				clients.add(client);
			}
		}
		return clients;
	}

	@Override
	public Client getClientById(int id) throws SQLException {
		try (Connection con = ConnectionUtility.getConnection()) {
			String sql = "SELECT * FROM Project0.client WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int client_id = rs.getInt("id");
				String name = rs.getString("name");

				Client client = new Client(client_id, name);
				return client;
			} else {
				return null; // if no client, return null
			}
		}
	}

//	@Override
//	public Client addClient(Client client) throws SQLException {
//		System.out.println("here in the addedclient methid in DAO");
//		try (Connection con = ConnectionUtility.getConnection()) {
//			// String sql = "INSERT INTO Project0.client (name) VALUES (?)";
//			String sql = "INSERT INTO Project0.client name = ?";
//			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//			pstmt.setString(1, client.getName());
//			pstmt.executeUpdate(); // exeuctUpdate for INSERT, UPDATE, DELETE. executeQuery for SELECT
//
//			int recordsUpdated = pstmt.executeUpdate();
//
//			if (recordsUpdated != 1) {
//				throw new SQLException("Could not insert new Client");
//			}
//
//			ResultSet generatedKeys = pstmt.getGeneratedKeys();
//			if (generatedKeys.next()) {
//				Client createdClient = new Client(generatedKeys.getInt(1), client.getName());
//
//				return createdClient;
//
//			} else {
//				throw new SQLException("Autogenerated id could not be generated for client");
//			}
//		}
//	}

	@Override
	public Client addClient(AddOrEditClientDTO name) throws SQLException {

		try (Connection con = ConnectionUtility.getConnection()) {
			String sql = "INSERT INTO Project0.client (name) VALUES (?)";

			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, name.getName());

			int recordsUpdated = pstmt.executeUpdate();

			if (recordsUpdated != 1) {
				throw new SQLException("Could not insert new Client");
			}

			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				Client createdClient = new Client(generatedKeys.getInt(1), name.getName());

				return createdClient;

			} else {
				throw new SQLException("Autogenerated id could not be generated for client");
			}
		}
	}

	@Override
	public Client editClient(int clientdId, AddOrEditClientDTO client) throws SQLException {
		try (Connection con = ConnectionUtility.getConnection()) {
			String sql = "UPDATE Project0.client SET name = ? WHERE id = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, client.getName());
			pstmt.setInt(2, clientdId);

			int recordUpdate = pstmt.executeUpdate(); // recordUpdate returns a int

			if (recordUpdate != 1) {
				throw new SQLException(" Record was not able to be updated");
			}

		}
		return new Client(clientdId, client.getName());
	}

	public void deleteClient(int clientId) throws SQLException {
		try (Connection con = ConnectionUtility.getConnection()) {
			String sql = "DELETE FROM Project0.client WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, clientId);

			int recordsDeleted = pstmt.executeUpdate();

			// if it is not 1, we know that no records were actually deleted
			if (recordsDeleted != 1) {
				throw new SQLException("Record was not able to be deleted");
			}
		}
	}
}
