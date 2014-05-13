
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
    ('administrator', 'adminpass', 'Admin', 'Admin', 'N/A'),
    ('bnc', 'bncpass', 'Bence', 'Kiglics', '1119 Budapest, Etele út 16. 1. em. 1.');

CREATE TABLE BOOKS (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    owner INTEGER NOT NULL,
    title CLOB NOT NULL,
    author CLOB NOT NULL,
    publisher CLOB NOT NULL,
    yearOfPublication INTEGER NOT NULL,
    condition INTEGER NOT NULL,
    soldTo INTEGER,
    PRIMARY KEY(id)
);

INSERT INTO BOOKS (owner, title, author, publisher, yearOfPublication, condition) VALUES
    (2, 'A C++ Programozási nyelv', 'Bjarne Stroustrup', 'Kiskapu', 1994, 87),
    (2, 'Számítógép-hálózatok', 'Andrew S. Tanenbaum', 'Addison-Weasley', 1994, 87);

CREATE TABLE ADVERTS (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    issuer INTEGER NOT NULL,
    book_id INTEGER NOT NULL,
    type CLOB NOT NULL,
    price DOUBLE NOT NULL,
    PRIMARY KEY(id)
);
