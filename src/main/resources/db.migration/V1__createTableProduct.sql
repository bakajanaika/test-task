CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS product
(
    id    UUID NOT NULL primary key default uuid_generate_v4(),
    name  VARCHAR(255),
    price DOUBLE PRECISION
);

insert into product (id, name, price)

values (uuid_generate_v4(), 'IPhone', 100),
       (uuid_generate_v4(), 'Headphones', 20),
       (uuid_generate_v4(), 'Phone Case', 10);