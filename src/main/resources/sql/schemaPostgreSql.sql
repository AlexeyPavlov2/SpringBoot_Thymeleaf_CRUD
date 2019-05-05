DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_category;

CREATE TABLE product_category
(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);
CREATE UNIQUE INDEX product_category_unique_idx ON product_category (name);

CREATE TABLE product
(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name    VARCHAR(150) NOT NULL,
    description VARCHAR(1500),
    price DECIMAL(20, 2),
    category_id INT NOT NULL,
    CONSTRAINT product_name_idx_unique UNIQUE (name),
    FOREIGN KEY (category_id) REFERENCES product_category (id) ON DELETE CASCADE

);
