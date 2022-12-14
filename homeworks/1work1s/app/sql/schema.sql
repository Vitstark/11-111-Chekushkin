DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS presentation;
DROP TABLE IF EXISTS concert;

CREATE TABLE person
(
    id         bigint PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name       varchar(60) NOT NULL,
    email      varchar(40) NOT NULL,
    pass       varchar(40) NOT NULL,
    role       varchar(10) NOT NULL
);

CREATE TABLE concert
(
    id          bigint PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    title       varchar(30) NOT NULL,
    author      varchar(50) NOT NULL,
    description text        NOT NULL,
    image_path  text        NOT NULL
);

CREATE TABLE presentation
(
    id                bigint PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    concert_id        bigint references concert (id),
    time              timestamp not null
);

create table ticket
(
    presentation_id bigint not null references presentation(id) on delete set null,
    row integer not null check (row > 0),
    place integer not null check (place > 0),
    owner_id bigint references person(id) on delete set null,

    constraint pk_ticket primary key (presentation_id, row, place)
);

create or replace function after_insert_presentation_insert_create_function()
    returns trigger
    language 'plpgsql'
as
$BODY$
BEGIN
INSERT
INTO ticket(presentation_id, row, place)
VALUES (new.id, 1, 1),
       (new.id, 1, 2),
       (new.id, 1, 3),
       (new.id, 1, 4),
       (new.id, 1, 5),
       (new.id, 1, 6),
       (new.id, 1, 7),
       (new.id, 1, 8),
       (new.id, 1, 9),
       (new.id, 1, 10),
       (new.id, 1, 11),
       (new.id, 1, 12),
       (new.id, 2, 1),
       (new.id, 2, 2),
       (new.id, 2, 3),
       (new.id, 2, 4),
       (new.id, 2, 5),
       (new.id, 2, 6),
       (new.id, 2, 7),
       (new.id, 2, 8),
       (new.id, 2, 9),
       (new.id, 2, 10),
       (new.id, 2, 11),
       (new.id, 2, 12),
       (new.id, 3, 1),
       (new.id, 3, 2),
       (new.id, 3, 3),
       (new.id, 3, 4),
       (new.id, 3, 5),
       (new.id, 3, 6),
       (new.id, 3, 7),
       (new.id, 3, 8),
       (new.id, 3, 9),
       (new.id, 3, 10),
       (new.id, 3, 11),
       (new.id, 3, 12),
       (new.id, 4, 1),
       (new.id, 4, 2),
       (new.id, 4, 3),
       (new.id, 4, 4),
       (new.id, 4, 5),
       (new.id, 4, 6),
       (new.id, 4, 7),
       (new.id, 4, 8),
       (new.id, 4, 9),
       (new.id, 4, 10),
       (new.id, 4, 11),
       (new.id, 4, 12),
       (new.id, 5, 1),
       (new.id, 5, 2),
       (new.id, 5, 3),
       (new.id, 5, 4),
       (new.id, 5, 5),
       (new.id, 5, 6),
       (new.id, 5, 7),
       (new.id, 5, 8),
       (new.id, 5, 9),
       (new.id, 5, 10),
       (new.id, 5, 11),
       (new.id, 5, 12),
       (new.id, 6, 1),
       (new.id, 6, 2),
       (new.id, 6, 3),
       (new.id, 6, 4),
       (new.id, 6, 5),
       (new.id, 6, 6),
       (new.id, 6, 7),
       (new.id, 6, 8),
       (new.id, 6, 9),
       (new.id, 6, 10),
       (new.id, 6, 11),
       (new.id, 6, 12),
       (new.id, 7, 1),
       (new.id, 7, 2),
       (new.id, 7, 3),
       (new.id, 7, 4),
       (new.id, 7, 5),
       (new.id, 7, 6),
       (new.id, 7, 7),
       (new.id, 7, 8),
       (new.id, 7, 9),
       (new.id, 7, 10),
       (new.id, 7, 11),
       (new.id, 7, 12),
       (new.id, 8, 1),
       (new.id, 8, 2),
       (new.id, 8, 3),
       (new.id, 8, 4),
       (new.id, 8, 5),
       (new.id, 8, 6),
       (new.id, 8, 7),
       (new.id, 8, 8),
       (new.id, 8, 9),
       (new.id, 8, 10),
       (new.id, 8, 11),
       (new.id, 8, 12);
RETURN old;
END;
$BODY$;

CREATE OR REPLACE TRIGGER after_insert_trigger
    AFTER INSERT
    ON presentation
    FOR EACH ROW
    EXECUTE FUNCTION after_insert_presentation_insert_create_function();