CREATE TABLE IF NOT EXISTS INVOICE (
    ID VARCHAR NOT NULL PRIMARY KEY,
    CLIENT VARCHAR NOT NULL,
    FROM_DATE DATE NOT NULL,
    TO_DATE DATE NOT NULL,
    AMOUNT NUMBER NOT NULL,
    TAX NUMBER NOT NULL,
    NOTES VARCHAR,
    VERSION NUMBER
);