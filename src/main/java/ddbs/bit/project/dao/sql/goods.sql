DROP TABLE IF EXISTS goods;

CREATE TABLE goods(
    id bigint primary key,
    user_id bigint not null,
    name varchar(40) not null,
    introduction varchar(100) not null,
    picture_u_r_l char(40) default 'fuck you',
    kind char(40) default 'unknown',
    original_price int,
    current_price int not null,
    amount int not null,
    upload_time int not null
);