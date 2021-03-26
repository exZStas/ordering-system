DROP TABLE IF EXISTS catalogue;
DROP TABLE IF EXISTS user_order;
DROP TABLE IF EXISTS catalogue_product;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_type;
DROP TABLE IF EXISTS product_type_attribute_value;
DROP TABLE IF EXISTS attribute_value;
DROP TABLE IF EXISTS attribute;
DROP SEQUENCE IF EXISTS ordering_system_sequence;

CREATE TABLE catalogue (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE catalogue_product (
  id INT AUTO_INCREMENT PRIMARY KEY,
  catalogue_id int,
  product_id int
);

CREATE TABLE product (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(250),
  stock int,
  product_type_id int
);

CREATE TABLE product_type (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(250)
);

CREATE TABLE product_type_attribute_value (
  id INT AUTO_INCREMENT PRIMARY KEY,
  product_type_id int,
  attribute_value_id int
);

CREATE TABLE attribute_value (
  id INT AUTO_INCREMENT PRIMARY KEY,
  attribute_id int,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(250),
  value int
);

CREATE TABLE attribute (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(250)
);

CREATE TABLE user_order (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(250),
  status VARCHAR(50) NOT NULL,
  creation_date DATE NOT NULL,
  product_id int
);

create sequence ordering_system_sequence;

ALTER TABLE catalogue_product ADD FOREIGN KEY (catalogue_id) references catalogue(id);
ALTER TABLE catalogue_product ADD FOREIGN KEY (product_id) references product(id);
ALTER TABLE product ADD FOREIGN KEY (product_type_id) references product_type(id);
ALTER TABLE product_type_attribute_value ADD FOREIGN KEY (product_type_id) references product_type(id);
ALTER TABLE product_type_attribute_value ADD FOREIGN KEY (attribute_value_id) references attribute_value(id);
ALTER TABLE attribute_value ADD FOREIGN KEY (attribute_id) references attribute(id);

CREATE UNIQUE INDEX uidx_product_type_attribute_value
ON product_type_attribute_value(product_type_id, attribute_value_id);

CREATE UNIQUE INDEX uidx_catalogue_product
ON catalogue_product(catalogue_id, product_id);

CREATE INDEX idx_product_name ON product(name);
CREATE INDEX idx_product_type ON product_type(name);
CREATE INDEX idx_attribute_value ON attribute_value(name);
CREATE INDEX idx_attribute ON attribute(name);
CREATE INDEX idx_order_status ON user_order(status);
CREATE INDEX idx_order_creation_date ON user_order(creation_date);
CREATE INDEX idx_order_name ON user_order(name);

ALTER TABLE product
ADD CONSTRAINT product_stock_chk
CHECK (stock >= 0);


