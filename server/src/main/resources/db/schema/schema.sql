
CREATE TABLE users (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    forename VARCHAR(32) NOT NULL,
    surname VARCHAR(32) NOT NULL,
    address VARCHAR(128) NOT NULL
);

INSERT INTO users (username, password, forename, surname, address) VALUES
    ('administrator', 'adminpass', 'Admin', 'Admin', 'N/A'),
    ('bnc', 'bncpass', 'Bence', 'Kiglics', '1119 Budapest, Etele út 16. 1. em. 1.');

CREATE TABLE books (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    owner INTEGER NOT NULL,
    title VARCHAR(64) NOT NULL,
    author VARCHAR(64) NOT NULL,
    publisher VARCHAR(32) NOT NULL,
    yearOfPublication INTEGER NOT NULL,
    condition INTEGER NOT NULL
);

INSERT INTO books ( owner, title, author, publisher, yearOfPublication, condition) VALUES
    (2, 'A C++ Programozási nyelv', 'Bjarne Stroustrup', 'Kiskapu', 1994, 87),
    (2, 'Számítógép-hálózatok', 'Andrew S. Tanenbaum', 'Addison-Weasley', 1994, 87);

CREATE TABLE adverts (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    issuer INTEGER NOT NULL,
    bookId INTEGER NOT NULL,
    type VARCHAR(16) NOT NULL,
    expires DATE NOT NULL,
    price DOUBLE NOT NULL,
    winner INTEGER
);

INSERT INTO adverts (issuer, bookId, type, expires, price) VALUES
    (2, 1, 'FIX_PRICE', datetime('now', '+1 hour', 'localtime'), 4300),
    (2, 2, 'AUCTION', datetime('now', '+7 day', 'localtime'), 2590);

CREATE TABLE bids (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    advertId INTEGER NOT NULL,
    price DOUBLE NOT NULL
);
