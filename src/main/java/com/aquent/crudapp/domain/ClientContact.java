package com.aquent.crudapp.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The client entity corresponding to the "client" and  client_contact tables in the database.
 */

public class ClientContact {

	@NotNull
	private Integer clientId;

	@NotNull
	private Integer personId;  

	@NotNull
	@Size(min = 1, max = 50, message = "First name is required with maximum length of 50")
	private String personFirstName;

	@NotNull
	@Size(min = 1, max = 50, message = "Last name is required with maximum length of 50")
	private String personLastName;


	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}      

	public String getPersonFirstName() {
		return  personFirstName;
	}

	public void setPersonFirstName(String  personFirstName) {
		this.personFirstName = personFirstName;
	}

	public String getPersonLastName() {
		return personLastName;
	}

	public void setPersonLastName(String personLastName) {
		this.personLastName= personLastName;
	}

}
