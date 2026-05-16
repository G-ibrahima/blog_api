CREATE TABLE IF NOT EXISTS comment (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comment_content TEXT NOT NULL,
    post_id BIGINT,
    FOREIGN KEY (post_id) REFERENCES posts(post_id)
);