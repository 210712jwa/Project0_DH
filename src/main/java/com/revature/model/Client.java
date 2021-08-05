package com.revature.model;

import java.util.Objects;

public class Client {

	private int id;
	private String name;

	public Client(String name) {
		super();
		this.name = name;
	}

	public Client(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	// getters/setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	// hashcode and equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	// toString
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + "]";
	}

}
