CREATE TABLE IF NOT EXISTS posts (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);