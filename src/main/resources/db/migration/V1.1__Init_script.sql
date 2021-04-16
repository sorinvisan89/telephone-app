CREATE TABLE contacts(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    UNIQUE KEY email_unique (email)
);

CREATE INDEX email_idx ON contacts(email);