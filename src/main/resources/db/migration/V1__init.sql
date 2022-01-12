create table if not exists cart (
    id bigserial primary key,
    login varchar(255)
);

create table if not exists purchase (
    id bigserial primary key,
    product_id bigint,
    price float,
    cart_id bigint references cart (id)
);

create table if not exists product (
    id bigserial primary key,
    title varchar(255),
    price float
);

insert into cart (login)
values ('Andrey');

insert into product(title, price)
values ('product1', 100),
       ('product2', 200),
       ('product3', 300),
       ('product4', 400),
       ('product5', 500),
       ('product6', 600),
       ('product7', 700),
       ('product8', 800),
       ('product9', 900),
       ('product10', 1000),
       ('product11', 2000),
       ('product12', 3000),
       ('product13', 4000),
       ('product14', 5000),
       ('product15', 6000),
       ('product16', 7000),
       ('product17', 8000),
       ('product18', 9000),
       ('product19', 1200),
       ('product20', 1300);