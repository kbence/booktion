
CREATE TABLE USERS (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    forename VARCHAR(32) NOT NULL,
    surname VARCHAR(32) NOT NULL,
    address VARCHAR(128) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO USERS (username, password, forename, surname, address) VALUES
    ('administrator', 'adminpass', 'Admin', 'Admin', 'N/A'),
    ('bnc', 'bncpass', 'Bence', 'Kiglics', '1119 Budapest, Etele út 16. 1. em. 1.');

CREATE TABLE BOOKS (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    owner INTEGER NOT NULL,
    title VARCHAR(64) NOT NULL,
    author VARCHAR(64) NOT NULL,
    publisher VARCHAR(32) NOT NULL,
    yearOfPublication INTEGER NOT NULL,
    condition INTEGER NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO BOOKS (owner, title, author, publisher, yearOfPublication, condition) VALUES
    (2, 'A C++ Programozási nyelv', 'Bjarne Stroustrup', 'Kiskapu', 1994, 87),
    (2, 'Számítógép-hálózatok', 'Andrew S. Tanenbaum', 'Addison-Weasley', 1994, 87);

CREATE TABLE ADVERTS (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    issuer INTEGER NOT NULL,
    bookId INTEGER NOT NULL,
    type VARCHAR(16) NOT NULL,
    expires TIME NOT NULL,
    price DOUBLE NOT NULL,
    winner INTEGER NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO ADVERTS (issuer, bookId, type, price) VALUES
    (2, 1, "FIX_PRICE", 4300),
    (2, 2, "AUCTION", 2590);

CREATE TABLE BIDS (
    id INTEGER NOT NULL,
    bidder INTEGER NOT NULL,
    advert INTEGER NOT NULL,
    price DOUBLE NOT NULL,
    PRIMARY KEY(id)
);
