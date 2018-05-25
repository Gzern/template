CREATE TABLE authority_info(
id INT PRIMARY KEY AUTO_INCREMENT,
authority_level INT UNIQUE,
authority_desc VARCHAR(255),
authority_ctime DATETIME
);

INSERT INTO authority_info (authority_level,authority_desc,authority_ctime) VALUES (0,'仅查询功能',NOW());
INSERT INTO authority_info (authority_level,authority_desc,authority_ctime) VALUES (1,'查询,新增，更新功能',NOW());
INSERT INTO authority_info (authority_level,authority_desc,authority_ctime) VALUES (2,'所有功能',NOW());
