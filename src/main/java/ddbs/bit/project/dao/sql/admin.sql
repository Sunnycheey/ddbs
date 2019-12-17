DROP TABLE IF EXISTS ADMIN0;
DROP TABLE IF EXISTS ADMIN1;

CREATE TABLE ADMIN0(
    id bigint primary key not null,
    username varchar(30) not null,
    // using sha256 (length of 32 bytes) to stored users password
    password char(64) not null,
    email varchar(40) unique not null
);

CREATE TABLE ADMIN1(
    id bigint primary key not null,
    username varchar(30) not null,
    password char(64) not null,
    email varchar(40)
);

CREATE INDEX admin_login_id_1 ON ADMIN0(email);
CREATE INDEX admin_login_id_2 ON ADMIN1(email);