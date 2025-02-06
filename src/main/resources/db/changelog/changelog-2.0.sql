--liquibase formatted sql

--changeset zhavokhir:2.0
INSERT INTO coffee(name)
VALUES ('Cappuccino'),
       ('Americano'),
       ('Espresso');
--rollback delete from coffee where name in ('Cappuccino', 'Americano', 'Espresso');

--changeset zhavokhir:2.1
INSERT INTO ingredient(title, total)
VALUES ('Water', 1000),
       ('Milk', 1000),
       ('Chocolate', 1000),
       ('Sugar', 1000);
--rollback delete from ingredient where title in ('Water', 'Milk', 'Chocolate', 'Sugar');

--changeset zhavokhir:2.2
INSERT INTO recipe(coffee_id, mass, ingredient_id) VALUES
    (1, 50, 1),
    (1, 60, 2),
    (1, 100, 3),
    (2, 200, 1),
    (2, 250, 4),
    (2, 300, 2),
    (3, 200, 1),
    (3, 150, 4),
    (3, 50, 3);
--rollback delete from recipe where coffee_id in (1, 2, 3);