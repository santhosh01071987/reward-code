/*-- schema.sql

-- Create the customer table
CREATE OR UPDATE TABLE customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);
--
-- Create the transaction table
CREATE OR UPDATE TABLE transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT,
    amount_spent DOUBLE,
    month VARCHAR(7), -- Format: YYYY-MM
    transaction_date TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);
*/