CREATE TABLE food
(
    food_id    binary(16) PRIMARY KEY,
    food_name  varchar(20) NOT NULL UNIQUE,
    category   varchar(20) NOT NULL,
    price      bigint      NOT NULL,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6) DEFAULT NULL
);

CREATE TABLE orders
(
    order_id     binary(16) PRIMARY KEY,
    phone_number varchar(13)  NOT NULL,
    address      varchar(200) NOT NULL,
    postcode     varchar(200) NOT NULL,
    order_status varchar(50)  NOT NULL,
    created_at   datetime(6)  NOT NULL,
    updated_at   datetime(6) DEFAULT NULL
);

CREATE TABLE order_item
(
    seq        bigint      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_id   binary(16)  NOT NULL,
    food_id binary(16)  NOT NULL,
    category   VARCHAR(50) NOT NULL,
    price      bigint      NOT NULL,
    quantity   int         NOT NULL,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6) DEFAULT NULL,
    INDEX (order_id),
    CONSTRAINT fk_order_item_to_order FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
    CONSTRAINT fk_order_item_to_food FOREIGN KEY (food_id) REFERENCES food (food_id)
);
