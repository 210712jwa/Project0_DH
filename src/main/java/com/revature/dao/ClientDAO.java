package com.revature.dao;

import java.sql.SQLException;
/**
 * methods here returns a client(s) back
	 DTOs are classes that are used to pass data around that might not completely conform to the actual "Model" class
 * @author david
 *
 */
import java.util.List;

import com.revature.dto.AddOrEditClientDTO;
import com.revature.model.Client;
/**
 * methods here returns a client(s) back 
 * DTOs are classes that are used to pass data around that might not completely conform to the actual "Model" class
 * @author david
 *
 */
public interface ClientDAO {

	public abstract List<Client> getAllClients() throws SQLException;

	/**
	 * ' This method returns a Client from the database
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */

	public abstract Client getClientById(int id) throws SQLException;

	/**
	 * This method returns a cClient from database based on id
	 * 
	 * @param string
	 * @return
	 * @throws SQLException
	 */

	public abstract Client addClient(AddOrEditClientDTO name) throws SQLException;
	
	/**
	 * This method adds a Client to the database
	 * 
	 * @param client
	 * @return
	 * @throws SQLException
	 */
	public abstract Client editClient(int clientId, AddOrEditClientDTO client) throws SQLException;

	/**
	 * This method edits a client info in the database
	 * 
	 * @param client
	 * @return
	 * @throws SQLException
	 */

	public abstract void deleteClient(int cliendId)  throws SQLException;
	/**
	 * This method deletes clients from database
	 */
	


}
