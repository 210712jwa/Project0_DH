package com.revature.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.ClientDAO;
import com.revature.exception.DatabaseException;
import com.revature.model.Client;

public class ClientServiceTest {

	private ClientService clientService;
	private ClientDAO clientDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.clientDao = mock(ClientDAO.class);
		
		this.clientService = new ClientService(clientDao);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_getAllClients_positive() throws DatabaseException, SQLException {
		
		List<Client> mockReturnValues = new ArrayList<>();
		mockReturnValues.add(new Client(1, "tom"));
		mockReturnValues.add(new Client(2, "david"));
		mockReturnValues.add(new Client(3, "jackson"));
		when(clientDao.getAllClients()).thenReturn(mockReturnValues);
		
		
		List<Client> actualClients = clientService.getAllClients();
		
		List<Client> expectedClients = new ArrayList<>();
		expectedClients.add(new Client(1, "tom"));
		expectedClients.add(new Client(2, "david"));
		expectedClients.add(new Client(3, "jackson"));
		
		assertEquals(expectedClients, actualClients);
			
		}
	
	@Test( expected = DatabaseException.class)
	public void test_getAllClients_negative() throws DatabaseException, SQLException {
		when(clientDao.getAllClients()).thenThrow(SQLException.class);
		
			clientService.getAllClients();
			
		}
	

	
	}
