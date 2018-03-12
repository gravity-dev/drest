/* Db schema */
CREATE DATABASE moi;
CREATE USER 'moi'@'localhost' IDENTIFIED BY 'moi';
GRANT ALL PRIVILEGES ON moi.* TO 'moi'@'localhost';

/* DDL */

USE moi;

-- GENERIC
CREATE TABLE locations ( 
   id int(11) NOT NULL AUTO_INCREMENT,
   street_address varchar(40),
   postal_code varchar(12),
   city varchar(30) NOT NULL,
   state_province varchar(25),
   country_id char(2),
   geo_coordinates varchar(256),
   PRIMARY KEY (id)
);

-- USER ACCOUNT
CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(30) NOT NULL,
  password varchar(64) NOT NULL,
  first_name varchar(30) NOT NULL,
  last_name varchar(30) NOT NULL,
  email varchar(75) NOT NULL,
  phone_code varchar(12) NOT NULL,
  phone varchar(32) NOT NULL,
  is_system_user tinyint(1) NOT NULL DEFAULT 0,
  is_active tinyint(1) NOT NULL,
  created_on datetime NOT NULL,
  last_updated datetime NOT NULL,
  location_id int(11) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY username_key (name),
  FOREIGN KEY (location_id) REFERENCES locations(id)
);

CREATE TABLE roles (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL,
  is_system_role tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY role_key (name)
);

CREATE TABLE user_role (
  role_id int(11) NOT NULL,
  user_id int(11) NOT NULL,
  UNIQUE KEY user_role_key (role_id,user_id),
  FOREIGN KEY (role_id) REFERENCES roles(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

/*
privileges model

CREATE TABLE auth_privileges (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL,
  is_system_privilege tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY privilege_key (name)
);

CREATE TABLE role_privileges (
  id int(11) NOT NULL AUTO_INCREMENT,
  role_id int(11) NOT NULL,
  privilege_id int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (privilege_id) REFERENCES auth_privileges(id),
  FOREIGN KEY (role_id) REFERENCES roles(id)
);

*/

-- STORES

CREATE TABLE stores (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL,
  owner_id int(11) NOT NULL,
  description varchar(1024),
  category varchar(64),
  is_active tinyint(1) NOT NULL,
  location_id int(11),
  created_on datetime NOT NULL,
  created_by datetime NOT NULL,
  last_updated datetime NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY productname_key (name),
  FOREIGN KEY (location_id) REFERENCES locations(id),
  FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE store_members (
  store_id int(11) NOT NULL,
  user_id int(11) NOT NULL,
  UNIQUE KEY user_store_key (store_id,user_id),
  FOREIGN KEY (store_id) REFERENCES stores(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- PRODUCTS
CREATE TABLE products (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL,
  description varchar(1024),
  category varchar(30),
  is_active tinyint(1) NOT NULL,
  created_on datetime NOT NULL,
  created_by datetime NOT NULL,
  last_updated datetime NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY productname_key (name)
);

CREATE TABLE product_catalog (
  id int(11) NOT NULL AUTO_INCREMENT,
  product_id int(11) NOT NULL,
  store_id int(11) NOT NULL,
  price decimal NOT NULL,
  is_active tinyint(1) NOT NULL,
  created_on datetime NOT NULL,
  created_by datetime NOT NULL,
  last_updated datetime NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY store_product_key (store_id,product_id),
  FOREIGN KEY (store_id) REFERENCES stores(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);