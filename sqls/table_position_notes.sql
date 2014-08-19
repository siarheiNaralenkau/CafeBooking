CREATE TABLE `table_position_notes` (
	`id` INT(11) NOT NULL,
	`position_notes` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='Stores details about venue\'s table position.'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

INSERT INTO table_position_notes VALUES(1, 'Common');
INSERT INTO table_position_notes VALUES(2, 'Near window');
INSERT INTO table_position_notes VALUES(3, 'Outdoors');