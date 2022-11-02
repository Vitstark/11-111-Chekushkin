DROP TABLE IF EXISTS person;

create table person
(
    id         bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY ,
    first_name varchar(20) NOT NULL,
    last_name  varchar(20),
    email      varchar(30) NOT NULL,
    pass       varchar(40) NOT NULL
);
