CREATE TABLE admin(
    id bigint primary key not null,
    username varchar(30) not null,
    password char(32) not null,
    email varchar(40)
)