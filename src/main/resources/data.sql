DROP TABLE IF EXISTS tbl_book;
CREATE TABLE tbl_book (id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255),  author VARCHAR(255));

INSERT INTO tbl_book (id, name, author) VALUES (1, 'Spring Boot - Spring Data JPA with Hibernate and H2 Web Console', 'jpa author');
INSERT INTO tbl_book (id, name, author) VALUES (2, 'Spring Boot - Spring Data JPA with Hibernate and H2 Web Console', 'jpa');