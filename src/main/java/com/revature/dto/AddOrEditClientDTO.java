package com.revature.dto;

/**
 * Postman sends request, javelin receives it and encapsulate into an object. we encapsulate specific property
 * @author David Huynh
 *
 */
public class AddOrEditClientDTO {

	private String name;
	
	public AddOrEditClientDTO() {
	super();	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

		
	}
	

