DROP TABLE IF EXISTS TBL_EMPLOYEES;

CREATE TABLE TBL_EMPLOYEES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS BALLOT;

CREATE TABLE BALLOT (
  ballot_id INT AUTO_INCREMENT  PRIMARY KEY,
  election_id INT NOT NULL,
  voter_id INT NOT NULL,
  submission_date TIMESTAMP DEFAULT NULL,
  submitted BOOLEAN DEFAULT NULL
);