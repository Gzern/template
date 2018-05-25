CREATE TABLE `template_stock_picture`(
stock_id BIGINT,
picture_url VARCHAR(255),
picture_ctime DATETIME,
CONSTRAINT FOREIGN KEY (stock_id) REFERENCES template_stock(id)
)