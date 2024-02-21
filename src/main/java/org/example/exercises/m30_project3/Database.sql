CREATE
    DATABASE sales;

CREATE TABLE customer
(
    id             SERIAL UNIQUE NOT NULL,
    name           VARCHAR(50)   NOT NULL,
    cpf            BIGINT        NOT NULL,
    phone          BIGINT        NOT NULL,
    address        VARCHAR(50)   NOT NULL,
    address_number BIGINT        NOT NULL,
    city           VARCHAR(50)   NOT NULL,
    state          VARCHAR(2)    NOT NULL,
    active         BOOLEAN       NOT NULL
);

CREATE TABLE product
(
    id          SERIAL UNIQUE  NOT NULL,
    code        VARCHAR(50)    NOT NULL,
    name        VARCHAR(150)   NOT NULL,
    description VARCHAR(255)   NOT NULL,
    price       NUMERIC(10, 2) NOT NULL,
    active      BOOLEAN
);

CREATE TABLE sale
(
    id          SERIAL UNIQUE      NOT NULL,
    code        VARCHAR(10) UNIQUE NOT NULL,
    customer_id BIGINT             NOT NULL,
    total_price NUMERIC(10, 2)     NOT NULL,
    created_at  TIMESTAMP          NOT NULL,
    status      VARCHAR(50)        NOT NULL,
    CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE product_amount
(
    id          SERIAL UNIQUE  NOT NULL,
    product_id  BIGINT         NOT NULL,
    sale_id     BIGINT         NOT NULL,
    amount      INT            NOT NULL,
    total_price NUMERIC(10, 2) NOT NULL,
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fk_sale_id FOREIGN KEY (sale_id) REFERENCES sale (id)
)