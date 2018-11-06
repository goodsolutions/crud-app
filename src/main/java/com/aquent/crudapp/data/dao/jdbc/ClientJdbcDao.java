package com.aquent.crudapp.data.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aquent.crudapp.data.dao.ClientDao;
import com.aquent.crudapp.domain.Client;
import com.aquent.crudapp.domain.ClientContact;
import com.aquent.crudapp.domain.Person;

/**
 * Spring JDBC implementation of {@link ClientDao}.
 */
public class ClientJdbcDao implements ClientDao {

    private static final String SQL_LIST_CLIENT = "SELECT * FROM client ORDER BY client_name";
    private static final String SQL_READ_CLIENT = "SELECT * FROM client WHERE client_id = :clientId";
    private static final String SQL_DELETE_CLIENT = "DELETE FROM client WHERE client_id = :clientId";
 
    private static final String SQL_UPDATE_CLIENT = "UPDATE client SET (client_name, client_uri, phone_number, street_address, city, state, zip_code)"
                                                  + " = (:clientName, :clientUri, :phoneNumber, :streetAddress, :city, :state, :zipCode)"
                                                  + " WHERE client_id = :clientId";
   
    private static final String SQL_CREATE_CLIENT = "INSERT INTO client (client_name, client_uri, phone_number, street_address, city, state, zip_code)"
                                                  + " VALUES (:clientName, :clientUri, :phoneNumber, :streetAddress, :city, :state, :zipCode)";
    
    private static final String SQL_CREATE_CLIENT_CONTRACT = "INSERT INTO client_contact (client_id, person_id) "
    		                                                  + " SELECT client.client_id, person.person_id from client, person "
    		                                                  + " WHERE  client.client_name = person.client_name "
    		                                                  + " AND person.person_id = :personId";
                                                                  
    private static final String SQL_UPDATE_CLIENT_CONTRACT = "UPDATE client_contact SET client_id = (SELECT client_id  "
    		                                                 + " FROM client_contact WHERE person_id = :personId ) "
    		                                                 + " WHERE person_id = :personId ";
    		
    
    private static final String SQL_LIST_CONTACT = "SELECT client_contact.client_id, client_contact.person_id, person.first_name, person.last_name "
    		                                       + " FROM client_contact, person WHERE client_contact.person_id = person.person_id "
    		                                       + " ORDER BY person.first_name, person.last_name, person.person_id";
    
    
    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Client> listClient() {
        return namedParameterJdbcTemplate.getJdbcOperations().query(SQL_LIST_CLIENT, new ClientRowMapper());
    }
    
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ClientContact> listClientContact() {
       return namedParameterJdbcTemplate.getJdbcOperations().query(SQL_LIST_CONTACT, new ContactRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Client readClient(Integer clientId) {
        return namedParameterJdbcTemplate.queryForObject(SQL_READ_CLIENT, Collections.singletonMap("clientId", clientId), new ClientRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deleteClient(Integer clientId) {
        namedParameterJdbcTemplate.update(SQL_DELETE_CLIENT, Collections.singletonMap("clientId", clientId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updateClient(Client client) {
        namedParameterJdbcTemplate.update(SQL_UPDATE_CLIENT, new BeanPropertySqlParameterSource(client));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createClient(Client client) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_CLIENT, new BeanPropertySqlParameterSource(client), keyHolder);
        return keyHolder.getKey().intValue();
    }
    
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createClientContract(Person person) {
    	Map<String, Integer> paramMap = new HashMap<String, Integer>();
    	paramMap.put("personId", person.getPersonId());
        namedParameterJdbcTemplate.update(SQL_CREATE_CLIENT_CONTRACT, paramMap );        
    	return person.getPersonId();   	
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updateClientContract(Person person) {
    	Map<String, Integer> paramMap = new HashMap<String, Integer>();
    	paramMap.put("personId", person.getPersonId());
        namedParameterJdbcTemplate.update(SQL_UPDATE_CLIENT_CONTRACT, paramMap );    	
    }
    
    
    /**
     * Row mapper for client records.
     */
    private static final class ClientRowMapper implements RowMapper<Client> {

        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            Client client = new Client();
            client.setClientId(rs.getInt("client_id"));
            client.setClientName(rs.getString("client_name"));
            client.setClientUri(rs.getString("client_uri"));
            client.setPhoneNumber(rs.getString("phone_number"));          
            client.setStreetAddress(rs.getString("street_address"));
            client.setCity(rs.getString("city"));
            client.setState(rs.getString("state"));
            client.setZipCode(rs.getString("zip_code"));                      
            return client;
        }
    }
    
    /**
     * Row mapper for contact records.
     */
    private static final class ContactRowMapper implements RowMapper<ClientContact> {
    	
        @Override
        public ClientContact mapRow(ResultSet rs, int rowNum) throws SQLException {
        	ClientContact clientContact = new ClientContact();
        	clientContact.setClientId(rs.getInt("client_contact.client_id"));
        	clientContact.setClientId(rs.getInt("client_contact.person_id"));                     
        	clientContact.setPersonFirstName(rs.getString("person.first_name"));
        	clientContact.setPersonLastName(rs.getString("person.last_name"));
            return clientContact;
        }
    }
    
}
 

