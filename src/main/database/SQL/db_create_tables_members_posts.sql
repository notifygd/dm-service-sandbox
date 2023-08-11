CREATE TABLE Members (
    member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    dob DATE,
    membership_date DATE,
    country VARCHAR(255),
    city VARCHAR(255),
    language VARCHAR(2)
);

CREATE TABLE Posts (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT,
    topic VARCHAR(255),
    title VARCHAR(255),
    description TEXT,
    source_contrib_factors TEXT,
    challenges TEXT,
    solutions TEXT,
    sentiment INT,
    emotion VARCHAR(50),
    related_posts TEXT,
    user_tags TEXT,
    SDG_tags_for_post TEXT,
    member_post_cnt INTEGER,
    member_upvotes INTEGER,
    member_share_cnt INTEGER,
    content_upvote_cnt INTEGER,
    content_share_cnt INTEGER,
    FOREIGN KEY (member_id) REFERENCES Members(member_id)
);


LOAD DATA INFILE 'members.csv'
INTO TABLE Members
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(first_name, last_name, dob, membership_date, country, city, language);


LOAD DATA INFILE 'posts.csv'
INTO TABLE Posts
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(member_id, topic, title, description, source_contrib_factors, challenges, solutions, sentiment, emotion, related_posts, user_tags, SDG_tags_for_post, member_post_cnt, member_upvotes, member_share_cnt, content_upvote_cnt, content_share_cnt);
