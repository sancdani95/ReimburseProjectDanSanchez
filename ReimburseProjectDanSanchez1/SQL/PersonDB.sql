DROP TABLE person

CREATE TABLE IF NOT EXISTS person(
	person_id SERIAL PRIMARY KEY,
	person_username varchar UNIQUE NOT NULL,
	person_password varchar NOT null,
	person_boss boolean DEFAULT FALSE
	)

SELECT * FROM person

SELECT * FROM ticket

INSERT INTO person VALUES (DEFAULT, 'bro', 'pass', DEFAULT)

INSERT INTO person VALUES (DEFAULT, 'sis', 'word', DEFAULT),(DEFAULT, 'mom', '1111', TRUE), 
(DEFAULT,'dad', '0000', TRUE)