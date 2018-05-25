CREATE TABLE `order_info`(
id INT PRIMARY KEY AUTO_INCREMENT,
op_user_id INT,
op_name VARCHAR(50),
op_role VARCHAR(50),
op_factory VARCHAR(255),
op_behavier VARCHAR(255),
op_amount INT,
op_address TEXT,
op_time DATETIME
)