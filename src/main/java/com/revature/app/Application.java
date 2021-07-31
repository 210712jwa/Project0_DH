package com.revature.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.revature.exception.ClientNotFoundException;
import com.revature.exception.DatabaseException;
import com.revature.model.Client;
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
//		try {
//			Connection connection = ConnectionUtility.getConnection();
//			
//			System.out.println(connection);
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		ClientService clientService = new ClientService();
		
		try {
			List<Client> clients = clientService.getAllClients();
			
			System.out.println(clients);
			
		}catch(DatabaseException e) {
			System.out.println(" Something went wrong with interacting with database: error 500");
		}

		try {
			Client client = clientService.getClientById(2);
			System.out.println(client);
		} catch (DatabaseException e) {
			System.out.println(" Something went wrong with interacting with database: error 500");
			e.printStackTrace();
		} catch (ClientNotFoundException e) {
			System.out.println( e.getMessage());
			e.printStackTrace();
		}
}
}
