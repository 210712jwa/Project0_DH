package com.revature.model;

import java.util.Objects;

public class Account {

	private int cliendId;
	private int accId;
	private String accType;
	private double balance;

	public Account() {
		super();
	}
	
	public Account( int clientId, int acc_id, String aType, double bal) {
		this.accId = acc_id;
		this.cliendId = clientId;
		this.accType = aType;
		this.balance = bal;
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}


	@Override
	public int hashCode() {
		return Objects.hash(accId, accType, balance, cliendId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accId == other.accId && Objects.equals(accType, other.accType) && balance == other.balance
				&& cliendId == other.cliendId;
	}


	@Override
	public String toString() {
		return "Account [cliendId=" + cliendId + ", accId=" + accId + ", accType=" + accType + ", balance=" + balance
				+ "]";
	}

	
	
	
	
}
