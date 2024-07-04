CREATE TABLE coupon
(
    id   UUID NOT NULL primary key default uuid_generate_v4(),
    code VARCHAR(255) unique
);

insert into coupon (id, code)
values (uuid_generate_v4(), 'P50'),
       (uuid_generate_v4(), 'S1000')