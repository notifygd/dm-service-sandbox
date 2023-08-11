-- create a MySQL user to connect the DB. Replace 'fake_password' with real pwd
CREATE USER 'digital7_dm_team'@'%' IDENTIFIED BY 'fake_password' PASSWORD EXPIRE NEVER;
GRANT SELECT,INSERT, UPDATE, DELETE ON * . * TO 'digital7_dm_team'@'%';
FLUSH PRIVILEGES;
-- SHOW GRANTS FOR 'digital7_dm_team'@'%';


-- to test in local environment, need to create user for localhost
-- CREATE USER 'digital7_dm_team'@'localhost' IDENTIFIED BY 'fake_password' PASSWORD EXPIRE NEVER;
-- GRANT SELECT,INSERT, UPDATE, DELETE ON * . * TO 'digital7_dm_team'@'localhost';
-- FLUSH PRIVILEGES;
-- SHOW GRANTS FOR 'digital7_dm_team'@'localhost';
