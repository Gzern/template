CREATE TABLE user_role(
user_id INT,
role_id INT,
CONSTRAINT FOREIGN KEY (user_id) REFERENCES user_info(id),
CONSTRAINT FOREIGN KEY (role_id) REFERENCES role_info(id),
PRIMARY KEY (user_id,role_id)
)