package com.juliett.core.Users.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class UsersModelDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String contact;
	private String address;
	private Boolean isAdmin;
	private String dateOfBirth;
	private String tokens;

	public UsersModelDTO() {
		super();
	}

	public UsersModelDTO(Long id, String firstName, String lastName, String email, String password, String contact,
			String address, Boolean isAdmin, String dateOfBirth, String tokens) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.address = address;
		this.isAdmin = isAdmin;
		this.dateOfBirth = dateOfBirth;
		this.tokens = tokens;
	}

	public UsersModelDTO(String email, String password, Boolean isAdmin) {
		super();
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public UsersModelDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getTokens() {
		return tokens;
	}

	public void setTokens(String tokens) {
		this.tokens = tokens;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UsersModelDTO [email=" + email + ", password=" + password + "]";
	}

}
