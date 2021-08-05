package com.revature.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.revature.exception.ClientNotFoundException;
import com.revature.exception.DatabaseException;
import com.revature.model.Client;
import com.revature.service.ClientIdTakenException;
import com.revature.service.ClientService;
import com.revature.util.ConnectionUtility;

public class Application {

	public Application() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
//
//		System.out.println(System.getenv("db_url"));
//		System.out.println(System.getenv("db_username"));
//		System.out.println(System.getenv("db_password"));
//		
//		

		
		ClientService clientService = new ClientService();
		
//		try {
//			List<Client> clients = clientService.getAllClients();
//			
//			System.out.println(clients);
//			
//		}catch(DatabaseException e) {
//			System.out.println(" Something went wrong with interacting with database: error ");
//		}
//
//		try {
//			Client client = clientService.getClientById(2);
//			System.out.println(client);
//		} catch (DatabaseException e) {
//			System.out.println(" Something went wrong with interacting with database: error");
//			e.printStackTrace();
//		} catch (ClientNotFoundException e) {
//			System.out.println( e.getMessage());
//			e.printStackTrace();
//		}
//		
		try {
			Client clientToInsert = new Client ("tester");
			
			Client createdClient = clientService.addClient(clientToInsert);
			System.out.println("Client added: " + createdClient);
		} catch (DatabaseException e ) {
			System.out.println(" Something went wrong with interacting with database");
		} catch (ClientIdTakenException e) {
			System.out.println( e.getMessage());
			e.printStackTrace();
		}
}
}
