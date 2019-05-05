DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_category;

CREATE TABLE product_category
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT product_category_name_idx_unique UNIQUE (name)
);

CREATE TABLE product
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(150) NOT NULL,
    description VARCHAR(1500),
    price DECIMAL(20, 2),
    category_id INT NOT NULL,
    CONSTRAINT product_name_idx_unique UNIQUE (name)
);

ALTER TABLE product ADD FOREIGN KEY (category_id) REFERENCES product_category(id);



