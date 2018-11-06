package com.aquent.crudapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aquent.crudapp.data.dao.ClientDao;
import com.aquent.crudapp.domain.Client;
import com.aquent.crudapp.domain.ClientContact;
import com.aquent.crudapp.domain.Person;

/**
 * Default implementation of {@link ClientService}.
 */
public class DefaultClientService implements ClientService {

	private ClientDao clientDao;
	private Validator validator;

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Client> listClient() {
		return clientDao.listClient();
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ClientContact> listClientContact() {
		return clientDao.listClientContact();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Client readClient(Integer id) {
		return clientDao.readClient(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public Integer createClient(Client client) {
		return clientDao.createClient(client);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public Integer createClientContract(Person person) {
		return clientDao.createClientContract(person);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void updateClient(Client client) {
		clientDao.updateClient(client);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)	
	public void updateClientContract(Person person) {
		clientDao.updateClientContract(person);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void deleteClient(Integer id) {
		clientDao.deleteClient(id);
	}

	@Override
	public List<String> validateClient(Client client) {
		Set<ConstraintViolation<Client>> violations = validator.validate(client);
		List<String> errors = new ArrayList<String>(violations.size());
		for (ConstraintViolation<Client> violation : violations) {
			errors.add(violation.getMessage());
		}
		Collections.sort(errors);
		return errors;
	}
}
