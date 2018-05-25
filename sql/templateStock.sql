CREATE TABLE `template_stock`(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
template_id VARCHAR(255),
template_amount INT,
template_suits DOUBLE,
template_areas VARCHAR(255),
template_factory VARCHAR(255),
template_store VARCHAR(255),
template_memo TEXT,
template_borrowed TINYINT(1),
template_ctime DATETIME,
CONSTRAINT FOREIGN KEY (template_id) REFERENCES template_info(template_id)
)