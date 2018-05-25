CREATE TABLE `templateInfo`(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
template_id VARCHAR(255) UNIQUE,
template_series VARCHAR(255),
template_norm VARCHAR(255),
pieces_per_suit INT,
template_type VARCHAR(20),
template_signaturer VARCHAR(20),
template_signature_time DATE,
template_color VARCHAR(20),
template_ctime DATETIME
)