package com.revature.dto;

/**
 * Postman sends request, javelin receives it and encapsulate into an object. we encapsulate specific property
 * @author David Huynh
 *
 */
public class AddOrEditClientDTO {

	private String name;
	private int id;
	
	public AddOrEditClientDTO() {
	super();	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {	// no setter for Id
		return id;
	}

	public void setId(int clientId) {
		this.id = clientId;
		
	}
	

}

