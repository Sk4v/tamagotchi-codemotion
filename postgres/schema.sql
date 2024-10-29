CREATE TABLE "PRODUCTS" (
    id BIGSERIAL PRIMARY KEY,             
    name VARCHAR(255) NOT NULL,        
    isin VARCHAR(12) NOT NULL UNIQUE,  
    asset VARCHAR(50) NOT NULL,         
    region VARCHAR(100),               
    volatility NUMERIC(5, 2),  -- Volatilità (espressa in percentuale con due decimali)
    strategy VARCHAR(100)
);

COPY "PRODUCTS" (isin, name, asset, region, volatility, strategy)
FROM '/docker-entrypoint-initdb.d/etf.csv' DELIMITER ',' CSV HEADER;