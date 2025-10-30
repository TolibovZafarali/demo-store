CREATE TABLE profiles (
    id BIGINT,
    bio TEXT,
    phone_number VARCHAR(10),
    date_of_birth DATE,
    loyalty_points INT UNSIGNED DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES users(id)
) ENGINE=InnoDB;

CREATE TABLE tags (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE user_tags (
    user_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, tag_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
) ENGINE=InnoDB;