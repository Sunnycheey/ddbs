DROP TABLE IF EXISTS USER0;
DROP TABLE IF EXISTS USER1;

CREATE TABLE USER0(
    id bigint primary key not null,
    username varchar(30) not null,
    password char(32) not null,
    email varchar(40)
);

CREATE TABLE USER1(
    id bigint primary key not null,
    username varchar(30) not null,
    password char(32) not null,
    email varchar(40)
)