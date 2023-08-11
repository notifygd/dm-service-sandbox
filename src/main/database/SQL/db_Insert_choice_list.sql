-- choice_list ---
USE `digital7_digital_momentum` ;

DELETE from `choice_list`;

INSERT INTO `choice_list`
(`table_name`,`col_name`,`choice_value`,`choice_label`)
VALUES
('user','user_role','moderator','Moderator');

INSERT INTO `choice_list`
(`table_name`,`col_name`,`choice_value`,`choice_label`)
VALUES
('user','user_role','admin','Admin');

INSERT INTO `choice_list`
(`table_name`,`col_name`,`choice_value`,`choice_label`)
VALUES
('user','user_role','user','Registered User');

-- SELECT * from `choice_list`;