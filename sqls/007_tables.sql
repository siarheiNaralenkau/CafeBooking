DROP TABLE IF EXISTS `tables`;

CREATE TABLE `tables` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`venue_id` INT(11) NOT NULL DEFAULT '0',
	`x_pos` INT(11) NOT NULL DEFAULT '0',
	`y_pos` INT(11) NOT NULL DEFAULT '0',
	`places` INT(11) NOT NULL DEFAULT '0',
	`number` INT(11) NOT NULL DEFAULT '0',
	`position_notes` INT(11) NOT NULL DEFAULT '1',
	`is_free` TINYINT(4) NOT NULL DEFAULT '1',
	`booked_places` INT(11) NOT NULL DEFAULT '0',
	`booked_time` TIMESTAMP NULL DEFAULT NULL,
	`photo_url` VARCHAR(200) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `fk_table_venue` (`venue_id`),
	INDEX `fk_position_notes` (`position_notes`),
	CONSTRAINT `fk_position_notes` FOREIGN KEY (`position_notes`) REFERENCES `table_position_notes` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `fk_table_venue` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COMMENT='Table stores information about tables for specified venue.'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;