PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;

DROP TABLE IF EXISTS cars;

CREATE TABLE cars (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    make TEXT NOT NULL,
    model TEXT NOT NULL,
    year INTEGER NOT NULL,
    fuelType TEXT NOT NULL,
    topSpeed REAL NOT NULL,
    price REAL NOT NULL,
    isElectric BOOLEAN NOT NULL
);

INSERT INTO cars VALUES(1,'BMW','7 Series',2023,'Gasoline',155.0,86000.0,0);
INSERT INTO cars VALUES(2,'Mercedes-Benz','S-Class',2023,'Gasoline',155.0,89000.0,0);
INSERT INTO cars VALUES(3,'Audi','A8',2023,'Gasoline',155.0,89000.0,0);
INSERT INTO cars VALUES(4,'Porsche','Panamera',2023,'Hybrid',180.0,95000.0,1);
INSERT INTO cars VALUES(5,'Bentley','Continental GT',2023,'Gasoline',160.0,220000.0,0);
-- Continue until at least 20 rows

COMMIT;
