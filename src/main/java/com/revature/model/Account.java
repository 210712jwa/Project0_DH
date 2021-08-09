package com.revature.model;

import java.util.Objects;

public class Account {

	String accountType = "";
	private int balance;
	private int cliendId;
	private String accType;
	private int accId;
	
	


	public Account(int client_id, int acc_id, String aType, int bal) {
		// TODO Auto-generated constructor stub
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getId() {
		return cliendId;
	}
	public void setId(int id) {
		this.cliendId = id;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountType, balance, cliendId);
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
		return Objects.equals(accountType, other.accountType) && balance == other.balance && cliendId == other.cliendId;
	}
	@Override
	public String toString() {
		return "Account [accountType=" + accountType + ", balance=" + balance + ", id=" + cliendId + "]";
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

	
	
	
}
