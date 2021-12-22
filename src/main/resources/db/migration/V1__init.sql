create table if not exists product
(
    id bigserial primary key,
    title varchar(255),
    price int
);

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