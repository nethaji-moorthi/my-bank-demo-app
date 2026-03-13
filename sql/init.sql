-- MySQL script for my-bank-demo-app
-- Create database
CREATE DATABASE IF NOT EXISTS my_bank_db;

-- Use database
USE my_bank_db;

-- Create accounts table (JPA will handle this, but for reference)
-- Note: With spring.jpa.hibernate.ddl-auto=update, this is optional
CREATE TABLE IF NOT EXISTS accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    mobile VARCHAR(255) NOT NULL,
    account_number VARCHAR(255) NOT NULL UNIQUE,
    ifsc VARCHAR(255) NOT NULL,
    timestamp_ust DATETIME NOT NULL,
    timestamp_local DATETIME NOT NULL
);

-- Insert sample data (optional)
INSERT INTO accounts (first_name, last_name, email, mobile, account_number, ifsc, timestamp_ust, timestamp_local) VALUES
('Nethaji', 'Moorthi', 'jmnethaji@gmail.com', '9095622490', '201040012345', 'IFSC-12345', NOW(), NOW());