package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			PreparedStatement pstmt = con.prepareStatement("sql");

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery(sql);
			if (rs.next()) {
				int client_id = rs.getInt("id");
				String name = rs.getString("name");

				Client client = new Client(client_id, name);
				return client;
			} else {
				return null;	// if no client, return null
			}
		}
	}

	@Override
	public Client addClient(Client client) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client editClient(Client client) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client addAccount(Client client) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client editAccount(Client client) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
