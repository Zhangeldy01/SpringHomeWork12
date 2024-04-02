create table if not exists notes
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    header VARCHAR(100) NOT NULL,
    content VARCHAR(255) NOT NULL,
    create_date TIMESTAMP NOT NULL
);