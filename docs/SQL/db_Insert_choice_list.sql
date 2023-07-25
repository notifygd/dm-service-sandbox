-- choice_list ---
DELETE from `dm_changemaker_sb`.`choice_list`;

INSERT INTO `dm_changemaker_sb`.`choice_list`
(`table_name`,`col_name`,`choice_value`,`choice_label`)
VALUES
('user','user_role','moderator','Moderator');

INSERT INTO `dm_changemaker_sb`.`choice_list`
(`table_name`,`col_name`,`choice_value`,`choice_label`)
VALUES
('user','user_role','admin','Admin');

INSERT INTO `dm_changemaker_sb`.`choice_list`
(`table_name`,`col_name`,`choice_value`,`choice_label`)
VALUES
('user','user_role','user','Registered User');

-- SELECT * from `dm_changemaker_sb`.`choice_list`;