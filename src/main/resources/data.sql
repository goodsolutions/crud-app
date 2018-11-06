INSERT INTO person (
    first_name,
    last_name,
    email_address,
    street_address,
    city,
    state,
    zip_code, 
    client_name
) VALUES (
    'John',
    'Smith',
    'fake1@aquent.com',
    '123 Any St.',
    'Asheville',
    'NC',
    '28801',
    'International Business Machines Corporation'
), (
    'Jane',
    'Smith',
    'fake2@aquent.com',
    '123 Any St.',
    'Asheville',
    'NC',
    '28801',
    'Hewlett Packard Company'
);
INSERT INTO client (
    client_name,
    client_uri,
    phone_number,
    street_address,
    city,
    state,
    zip_code
) VALUES (
    'International Business Machines Corporation', 
    'http://www.ibm.com',
    '914-499-1900',
    '1 New Orchard Road',
    'Armonk',
    'NY',
    '10504'
 ), (    
    'Hewlett Packard Company', 
    'http://www.hp.com',
    '650-857-1501',
    '1501 Page Mill Road',
    'Palo Alto',
    'CA',
    '94304'
);
INSERT INTO client_contact (
    client_id,
    person_id
) VALUES (
    0, 
    0
), (
    1,
    1
);
