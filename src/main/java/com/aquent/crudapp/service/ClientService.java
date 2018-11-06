package com.aquent.crudapp.service;

import java.util.List;
import com.aquent.crudapp.domain.Client;
import com.aquent.crudapp.domain.ClientContact;
import com.aquent.crudapp.domain.Person;

/**
 * Client operations.
 */
public interface ClientService {

	/**
	 * Retrieves all of the client records.
	 *
	 * @return list of client records
	 */
	List<Client> listClient();
		
	/**
	 * Retrieves all of the client records.
	 *
	 * @return list of client contact records
	 */	
	List<ClientContact> listClientContact();

	/**
	 * Creates a new client record.
	 *
	 * @param client the values to save
	 * @return the new client ID
	 */
	Integer createClient(Client client);
	
	/**
	 * Creates a new client contract record.
	 *
	 * @param person the values for person Id
	 * @return the new client ID
	 */
	Integer createClientContract(Person person);

	/**
	 * Retrieves a client record by ID.
	 *
	 * @param id the client ID
	 * @return the client record
	 */
	Client readClient(Integer id);

	/**
	 * Updates an existing client record.
	 *
	 * @param client the new values to save
	 */
	void updateClient(Client client);
	
	/**
	 * Updates an existing client contact record.
	 *
	 * @param client contact the new values to save
	 */
	void updateClientContract(Person person);

	/**
	 * Deletes a client record by ID.
	 *
	 * @param id the client ID
	 */
	void deleteClient(Integer id);

	/**
	 * Validates populated client data.
	 *
	 * @param client the values to validate
	 * @return list of error messages
	 */
	List<String> validateClient(Client client);
}

