package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.revature.dao.ClientDAO;
import com.revature.dao.ClientDAOImpl;
import com.revature.dto.AddOrEditClientDTO;
import com.revature.exception.BadParameterException;
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

	public Client getClientById(String id) throws DatabaseException, ClientNotFoundException {
		try {
			int clientId = Integer.parseInt(id);

			Client client = clientDao.getClientById(clientId);

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

	public Client editClient(String stringId, AddOrEditClientDTO client)
			throws DatabaseException, ClientNotFoundException {

		try {
			int clientId = Integer.parseInt(stringId);

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

	public Client deleteClient(String clientid) throws DatabaseException, BadParameterException, ClientNotFoundException {
		try {
			int id = Integer.parseInt(clientid);

			Client client = clientDao.getClientById(id);
			if (client == null) {
				throw new ClientNotFoundException("Client with an id " + id + " does not exist");
			}

			clientDao.deleteClient(id);
			System.out.println("Client " + id + " has been deleted");

		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with our DAO operations");
		} catch (NumberFormatException e) {
			throw new BadParameterException( clientid + " was passed in by the user as the id, " + "but it is not an int");
		}
		return null;
	}
}
