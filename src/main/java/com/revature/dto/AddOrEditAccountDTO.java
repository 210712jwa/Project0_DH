package com.revature.dto;

public class AddOrEditAccountDTO {

	private int clientId;
	private String accType;
	private int accNumber;
	
	public int getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}

	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	
	
}
