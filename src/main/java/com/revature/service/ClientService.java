package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.revature.dao.ClientDAO;
import com.revature.dao.ClientDAOImpl;
import com.revature.dto.AddOrEditClientDTO;
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

	public Client addClient(AddOrEditClientDTO client) throws DatabaseException, ClientIdTakenException {
		try {

			Client addedClient = clientDao.addClient(client);

			return addedClient;

		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with DAO operations");
		}

	}

	public Client editClient(int clientId, AddOrEditClientDTO client)
			throws DatabaseException, ClientNotFoundException {

		try {
			if (clientDao.getClientById(clientId) == null) {
				throw new ClientNotFoundException("The client with id " + clientId + " was not found");
			}

			System.out.println(clientId);
			Client editedClient = clientDao.editClient(clientId, client);

			return editedClient;
		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with DAO operation");
		}
	}

}
