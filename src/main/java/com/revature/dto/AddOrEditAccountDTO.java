package com.revature.dto;

public class AddOrEditAccountDTO {

	private String accType;
	private double balance;
	private int clientId;

	private double dBalance;
	
	public double getdBalance() {
		return dBalance;
	}
	public void setdBalance(double dBalance) {
		this.dBalance = dBalance;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	

}
