
CREATE TABLE USERS (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username CLOB NOT NULL,
    password CLOB NOT NULL,
    forename CLOB NOT NULL,
    surname CLOB NOT NULL,
    address CLOB NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO USERS (username, password, forename, surname, address) VALUES
    ('administrator', 'adminpass', 'Admin', 'Admin', 'N/A');

CREATE TABLE BOOKS (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    title CLOB NOT NULL,
    author CLOB NOT NULL,
    publisher CLOB NOT NULL,
    yearOfPublication INT NOT NULL,
    condition INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE ADVERTS (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    issuer INTEGER NOT NULL,
    book_id INTEGER NOT NULL,
    type CLOB NOT NULL,
    price DOUBLE NOT NULL,
    PRIMARY KEY(id)
);
