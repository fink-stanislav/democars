CREATE TABLE APP.ADDRESS (
	ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
	CITY VARCHAR(25),
	STREET VARCHAR(25),
	PRIMARY KEY (ID)
);
CREATE TABLE APP.CAR (
	ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
	MODEL INTEGER NOT NULL,
	PRICE DOUBLE,
	MILEAGE INTEGER,
	VIN VARCHAR(25),
	CONDITION VARCHAR(25),
	FEATURES INTEGER NOT NULL,
	SELLER INTEGER,
	PRIMARY KEY (ID)
);
CREATE TABLE APP.FEATURES (
	ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
	BODYTYPE VARCHAR(25),
	FUEL VARCHAR(25),
	TRANSMISSION VARCHAR(25),
	EXTERIORCOLOR VARCHAR(25),
	INTERIORCOLOR VARCHAR(25),
	ENGINE VARCHAR(25),
	PRIMARY KEY (ID)
);
CREATE TABLE APP.INDIVIDUALSELLER (
	ID INTEGER NOT NULL,
	FIRSTNAME VARCHAR(25),
	LASTNAME VARCHAR(25),
	PRIMARY KEY (ID)
);
CREATE TABLE APP.LEGALSELLER (
	ID INTEGER NOT NULL,
	COMPANYNAME VARCHAR(25),
	PRIMARY KEY (ID)
);
CREATE TABLE APP.MODEL (
	ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
	MODEL VARCHAR(25),
	MAKE VARCHAR(25),
	PRIMARY KEY (ID)
);
CREATE TABLE APP.SELLER (
	ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
	ADDRESS INTEGER,
	DTYPE VARCHAR(31),
	PRIMARY KEY (ID)
);
