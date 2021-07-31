package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Client;

public interface ClientDAO {

		public abstract List<Client> getAllClients() throws SQLException;
		/**'
		 * This method returns a Client from the database
		 * @param id
		 * @return
		 * @throws SQLException
		 */
			
		public abstract Client getClientById(int id) throws SQLException;
		/**
		 * This method returns a cClient from database based on id
		 * @param client
		 * @return
		 * @throws SQLException
		 */
		
		public abstract Client addClient(Client client) throws SQLException;
		/**
		 * This method adds a Client to the database
		 * @param client
		 * @return
		 * @throws SQLException
		 */
		public abstract Client editClient(Client client) throws SQLException;
		/**
		 * This method edits a client info in the database 
		 * @param client
		 * @return
		 * @throws SQLException
		 */
		
		public abstract Client addAccount(Client client) throws SQLException;
		public abstract Client editAccount(Client client) throws SQLException;
		
		
		
		
		
		
		
}
