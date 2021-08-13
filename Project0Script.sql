
DROP TABLE IF EXISTS client;


CREATE TABLE client(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL
);

INSERT INTO client (name)
VALUES
('tom'),
('david'),
('jackson');


UPDATE client 
SET name = 'test'
WHERE id = 3;

DELETE client
FROM client
WHERE id = 2;

SELECT *
FROM client;

SELECT * 
FROM Project0.client 
WHERE id =3


-----------------------------------------------------
DROP TABLE IF EXISTS account;

CREATE TABLE account(
	accId INTEGER PRIMARY KEY AUTO_INCREMENT,
	clientId INTEGER NOT NULL,
	accType VARCHAR(255) NOT NULL,
	balance DOUBLE NOT NULL,
	CONSTRAINT `fk_clientAccount` FOREIGN KEY (clientId) REFERENCES client(id)
	);
	
INSERT INTO account (clientId, accType, balance)
VALUES
(1, "checking", 900.34),
(1, "saving", 1999.67),
(1, "saving", 40000.67),
(2, "saving", 90.67),
(2, "checking", 50000.66);

SELECT *
FROM account
WHERE clientId = 1 AND accId = 2;

SELECT *
FROM account 
WHERE clientId = 1 && balance > 400.00 && balance <2000.00;

SELECT a.id AS a.accId, 

UPDATE account
SET balance = 900
WHERE accId = 1;

DELETE account
FROM account
WHERE accId = 2;

SELECT * FROM Project0.account p WHERE p.accId = 1 AND p.clientId = 1;