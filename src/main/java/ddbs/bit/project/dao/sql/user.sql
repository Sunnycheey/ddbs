DROP TABLE IF EXISTS USER0;
DROP TABLE IF EXISTS USER1;

CREATE TABLE USER0(
    id bigint primary key not null,
    username varchar(30) not null,
    password char(64) not null,
    email varchar(40) unique not null
);

CREATE TABLE USER1(
    id bigint primary key not null,
    username varchar(30) not null,
    password char(64) not null,
    email varchar(40) unique not null
);

CREATE INDEX user_login_id_1 ON USER0(email);
CREATE INDEX user_login_id_2 ON USER1(email);