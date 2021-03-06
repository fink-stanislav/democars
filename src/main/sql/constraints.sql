ALTER TABLE APP.SELLER
ADD CONSTRAINT SELLER_ADDRESS
	FOREIGN KEY (ADDRESS)
	REFERENCES APP.ADDRESS (ID);

ALTER TABLE APP.INDIVIDUALSELLER
ADD CONSTRAINT IND_SELLER
	FOREIGN KEY (ID)
	REFERENCES APP.SELLER (ID);

ALTER TABLE APP.LEGALSELLER
ADD CONSTRAINT LEG_SELLER
	FOREIGN KEY (ID)
	REFERENCES APP.SELLER (ID);

ALTER TABLE APP.CAR
ADD CONSTRAINT CAR_SELLER
	FOREIGN KEY (SELLER)
	REFERENCES APP.SELLER (ID);

ALTER TABLE APP.CAR
ADD CONSTRAINT CAR_MODEL
	FOREIGN KEY (MODEL)
	REFERENCES APP.MODEL (ID);

ALTER TABLE APP.CAR
ADD CONSTRAINT CAR_FEATURES
	FOREIGN KEY (FEATURES)
	REFERENCES APP.FEATURES (ID);
