CREATE TABLE Members (
    member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    dob DATE,
    membership_date DATE,
    country VARCHAR(255),
    city VARCHAR(255),
    member_post_cnt INT,
    member_upvotes INT,
    member_share_cnt INT
);

CREATE TABLE posts (
    post_id VARCHAR(255) PRIMARY KEY,
    member_id VARCHAR(255),
    topic VARCHAR(255),
    title VARCHAR(255),
    description TEXT,
    source_contrib_factors TEXT,
    challenges TEXT,
    solutions TEXT,
    sentiment INT,
    emotion VARCHAR(255),
    related_posts TEXT,
    user_tags_for_post TEXT,
    SDG_tags_for_post TEXT,
    post_upvote_cnt INT,
    post_share_cnt INT,
    language VARCHAR(10),
    creation_timestamp DATETIME,
    update_timestamp DATETIME,
    FOREIGN KEY (member_id) REFERENCES members(member_id)
);



LOAD DATA INFILE 'members.csv'
INTO TABLE members
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(member_id, first_name, last_name, dob, membership_date, country, city);


LOAD DATA INFILE 'posts.csv'
INTO TABLE posts
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(post_id, member_id, topic, title, description, source_contrib_factors, challenges, solutions, sentiment, emotion, related_posts, user_tags_for_post, SDG_tags_for_post, post_upvote_cnt, post_share_cnt, language, creation_timestamp, update_timestamp);
