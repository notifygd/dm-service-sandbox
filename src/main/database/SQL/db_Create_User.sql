-- create sandbox user. 
CREATE USER 'changemaker_t'@'%' IDENTIFIED BY 'fake_password' PASSWORD EXPIRE NEVER;
GRANT SELECT,INSERT, UPDATE, DELETE ON * . * TO 'changemaker_t'@'%';
FLUSH PRIVILEGES;
-- SHOW GRANTS FOR 'changemaker_t'@'%';

-- to test in local environment, need to create user for localhost
CREATE USER 'changemaker_t'@'localhost' IDENTIFIED BY 'fake_password' PASSWORD EXPIRE NEVER;
GRANT SELECT,INSERT, UPDATE, DELETE ON * . * TO 'changemaker_t'@'localhost';
FLUSH PRIVILEGES;
-- SHOW GRANTS FOR 'changemaker_t'@'localhost';
