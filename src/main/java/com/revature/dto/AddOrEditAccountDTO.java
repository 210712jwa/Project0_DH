package com.revature.dto;

public class AddOrEditAccountDTO {

	private String accType;
	private String balance;
	private String clientId;
	private int intClientId;
	private double dBalance;
	
	
	public int getIntClientId() {
		return intClientId;
	}
	public void setIntClientId(int intClientId) {
		this.intClientId = intClientId;
	}
	public double getdBalance() {
		return dBalance;
	}
	public void setdBalance(double dBalance) {
		this.dBalance = dBalance;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	

}
