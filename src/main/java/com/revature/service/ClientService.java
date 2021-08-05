package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.revature.dao.ClientDAO;
import com.revature.dao.ClientDAOImpl;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.DatabaseException;
import com.revature.model.Client;
import com.revature.util.ConnectionUtility;

public class ClientService {

	private ClientDAO clientDao;

	public ClientService() {
		this.clientDao = new ClientDAOImpl();
	}

	public List<Client> getAllClients() throws DatabaseException {
		try {
			List<Client> clients = clientDao.getAllClients();

			return clients;
		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with DAO operation");
		}
	}

	public Client getClientById(int id) throws DatabaseException, ClientNotFoundException {
		try {

			Client client = clientDao.getClientById(id);

			if (client == null) {
				throw new ClientNotFoundException(" Client with id " + id + " was not found");
			}

			return client;
		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with DAO operation");
		}
	}

	public Client addClient(Client client) throws DatabaseException, ClientIdTakenException {
		try {
			
			
			Client addedClient = clientDao.addClient(client.getName());
			
			
			return addedClient;

		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with DAO operations");
		}

	}

}
