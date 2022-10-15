DROP TABLE ticket

CREATE TABLE IF NOT EXISTS ticket(
	ticket_id SERIAL PRIMARY KEY,
	ticket_username varchar NOT NULL,
	ticket_amount NUMERIC NOT NULL,
	ticket_discription varchar NOT NULL,
	ticket_status varchar DEFAULT 'pending'
)

SELECT * FROM ticket

INSERT INTO ticket VALUES (DEFAULT, 'sis', 200, 'For shoes', DEFAULT)

INSERT INTO ticket VALUES (DEFAULT, 'bro', 250, 'For sneakers', DEFAULT), 
(DEFAULT, 'bro', 5, 'For gum', DEFAULT)

UPDATE ticket SET ticket_discription = 'For sneakers' WHERE ticket_id =2