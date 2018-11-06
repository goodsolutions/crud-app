package com.aquent.crudapp.data.dao;

import com.aquent.crudapp.domain.Client;
import com.aquent.crudapp.domain.ClientContact;
import com.aquent.crudapp.domain.Person;

import java.util.List;

/**
 * Operations on the "client" table.
 */
public interface ClientDao {

	/**
	 * Retrieves all of the client records.
	 *
	 * @return list of client records
	 */
	List<Client> listClient();

	/**
	 * Retrieves all of the client Contact records.
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
	 * @param person the values to save
	 * @return the new person ID
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
	 * Updates an existing client contract record.
	 *
	 * @param client the new values to save
	 */
	void updateClientContract(Person person);

	/**
	 * Deletes a client record by ID.
	 *
	 * @param id the client ID
	 */
	void deleteClient(Integer id);
}

