CREATE TABLE IF NOT EXISTS susers (
    user_id INT primary KEY UNIQUE,
    user_guid VARCHAR(32) UNIQUE NOT NULL,
    user_name VARCHAR(64)
);
