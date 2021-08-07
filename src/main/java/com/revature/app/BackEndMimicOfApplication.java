//package com.revature.app;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.List;
//
//import com.revature.dto.AddOrEditClientDTO;
//import com.revature.exception.BadParameterException;
//import com.revature.exception.ClientNotFoundException;
//import com.revature.exception.DatabaseException;
//import com.revature.model.Client;
//import com.revature.service.ClientIdTakenException;
//import com.revature.service.ClientService;
//import com.revature.util.ConnectionUtility;
//
//public class BackEndMimicOfApplication {
//
//	public BackEndMimicOfApplication() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public static void main(String[] args) {
//		
//
//		System.out.println(System.getenv("db_url"));
//		System.out.println(System.getenv("db_username"));
//		System.out.println(System.getenv("db_password"));
//		
//		
//
//		
//		ClientService clientService = new ClientService();
//		// get client by id
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
//		
//		// add client
//		try {
//			//Client clientToInsert = new Client ("tester2");
//			
//			AddOrEditClientDTO clientDto = new AddOrEditClientDTO();
//					clientDto.setName("rebecca");
//			
//			Client createdClient = clientService.addClient(clientDto);
//			System.out.println("Client added: " + createdClient);
//			
//		} catch (DatabaseException e ) {
//			System.out.println(" Something went wrong with interacting with database");
//		} catch (ClientIdTakenException e) {
//			System.out.println( e.getMessage());
//			e.printStackTrace();
//		}
//		
//		// edit a client info
//		try {
//			AddOrEditClientDTO clientDto = new AddOrEditClientDTO();
//			clientDto.setName("EditedName");
//			
//			Client client = clientService.editClient("1", clientDto);
//			System.out.println(client);
//
//		}catch(ClientNotFoundException e) {
//			System.out.println(e.getMessage());
//		}catch (DatabaseException e) {
//			System.out.println(" Something went wrong with interacting with database");
//		}
//}
//	
//		// delete client
//		try {
//	
//			AddOrEditClientDTO clientDto = new AddOrEditClientDTO();
//	
//			
//			Client deletedClient = clientService.deleteClient("5");
//			
//
//		}catch(ClientNotFoundException e) {
//			System.out.println(e.getMessage());
//		}catch(BadParameterException e) {
//			System.out.println(e.getMessage());
//		}catch (DatabaseException e) {
//			System.out.println(e.getMessage());
//		}
//}
//}
