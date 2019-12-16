DROP TABLE IF EXISTS ADMIN0;
DROP TABLE IF EXISTS ADMIN1;

CREATE TABLE ADMIN0(
    id bigint primary key not null,
    username varchar(30) not null,
    password char(32) not null,
    email varchar(40)
);

CREATE TABLE ADMIN1(
    id bigint primary key not null,
    username varchar(30) not null,
    password char(32) not null,
    email varchar(40)
)
