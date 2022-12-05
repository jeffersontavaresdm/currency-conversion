CREATE TABLE asset_currency
(
    id               BIGSERIAL PRIMARY KEY,
    code             VARCHAR(10)      NOT NULL,
    codeIn           VARCHAR(10)      NOT NULL,
    name             VARCHAR(255)     NOT NULL,
    high             DOUBLE PRECISION NOT NULL,
    low              DOUBLE PRECISION NOT NULL,
    saleValue        FLOAT            NOT NULL,
    percentageChange FLOAT            NOT NULL,
    timestamp        BIGINT           NOT NULL,
    createDate       VARCHAR(255)     NOT NULL
);

CREATE UNIQUE INDEX asset_currency_code_codein_index ON asset_currency (timestamp);