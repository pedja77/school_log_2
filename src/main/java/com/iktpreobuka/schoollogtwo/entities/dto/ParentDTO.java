package com.iktpreobuka.schoollogtwo.entities.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParentDTO {

	@NotNull(message = "First name must be provided.")
	@Size(min = 2, max = 30, message ="First name must be between {min} and {max} characters long.")
	@JsonProperty(value = "First name")
	private String firstName;
	
	@NotNull(message = "Last name must be provided.")
	@Size(min = 2, max = 30, message = "Last name must be between {min} and {max} characters long.")
	@JsonProperty(value = "Last name")
	private String lastName;
	
	@NotNull(message = "Username must be provided.")
	@Size(min = 4, max = 8, message = "Username must be between {min} and {max} characters long.")
	@Pattern(regexp = "[a-zA-Z0-9_]", message = "Alphanumeric characters and underscore only.")
	@JsonProperty(value = "Username")
	private String username;
	
	@NotNull(message = "Password must be provided.")
	@Size(min = 5, max = 10, message = "Password must be between {min} and {max} characters long.")
	@JsonProperty(value = "Password")
	private String password;
	
	@NotNull(message = "Confirmed password must be provided")
	@Size(min = 5, max = 10, message = "Confirmed password must be between {min} and {max} characters long and must match with password.")
	@JsonProperty(value = "Confirmed password")
	private String repeatedPassword;
	
	@NotNull(message = "Email must be provided")
	@Email(message = "Email is not valid.")
	@JsonProperty(value = "Email")
	private String email;
	
	public ParentDTO() {
		super();
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}
	
	
}
