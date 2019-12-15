DROP TABLE IF EXISTS EMPLOYEE;
DROP TABLE IF EXISTS DEPARTMENT;
DROP TABLE IF EXISTS ADDRESS;

CREATE TABLE EMPLOYEE (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  department VARCHAR(250) DEFAULT NULL,
  address VARCHAR(250) DEFAULT NULL
);

CREATE TABLE DEPARTMENT (
  dep_id INT AUTO_INCREMENT  PRIMARY KEY,
  dep_code VARCHAR(250) NOT NULL,
  dep_name VARCHAR(250) NOT NULL
);

CREATE TABLE ADDRESS (
  add_id INT AUTO_INCREMENT  PRIMARY KEY,
  city_code VARCHAR(250) NOT NULL,
  city_name VARCHAR(250) NOT NULL
);