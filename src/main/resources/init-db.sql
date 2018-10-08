CREATE TABLE QuoteCollection (
    name VARCHAR NOT NULL,
    symbols VARCHAR NOT NULL,
   PRIMARY KEY (name)
);

INSERT INTO QuoteCollection VALUES('test-collection', 'GOOG,AAPL,MSFT,FB');


