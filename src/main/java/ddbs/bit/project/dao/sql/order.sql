DROP TABLE IF EXISTS orders;

CREATE TABLE orders(
    id long primary key,
    good_id long not null,
    user_id long not null,
    total_price int not null,
    goods_number smallint not null,
    address varchar(50) not null,
    phone_number char(11) not null,
    order_time date not null,
    order_state char(2) not null
)