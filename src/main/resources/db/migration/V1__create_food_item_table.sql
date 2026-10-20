CREATE TABLE tb_food_item (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    food_category VARCHAR(20) NOT NULL,
    expiration_date DATE
);