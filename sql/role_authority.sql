CREATE TABLE role_authority(
role_id INT,
authority_id INT,
CONSTRAINT FOREIGN KEY (role_id) REFERENCES role_info(id),
CONSTRAINT FOREIGN KEY (authority_id) REFERENCES authority_info(id),
PRIMARY KEY (role_id,authority_id)
)