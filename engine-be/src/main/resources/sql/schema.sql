CREATE TABLE PRODUCTS (
    id BIGSERIAL PRIMARY KEY,             
    name VARCHAR(255) NOT NULL,        
    isin VARCHAR(12) NOT NULL UNIQUE,  
    asset VARCHAR(50) NOT NULL,         
    region VARCHAR(100),               
    volatility NUMERIC(5, 2),  -- Volatilità (espressa in percentuale con due decimali)
    strategy VARCHAR(100)
);

INSERT INTO PRODUCTS (isin, name, asset, region, volatility, strategy)
SELECT * FROM CSVREAD('classpath:/csv/etf.csv');