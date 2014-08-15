CREATE TABLE `venue_schedule` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`venue_id` INT(11) NOT NULL DEFAULT '0',
	`day` INT(11) NULL DEFAULT '0' COMMENT 'Day of the week with special schedule',
	`open_time` TIME NOT NULL DEFAULT '00:00:00',
	`close_time` TIME NOT NULL DEFAULT '00:00:00',
	PRIMARY KEY (`id`),
	INDEX `fk_venue_schedule` (`venue_id`),
	INDEX `fk_week_day` (`day`),
	CONSTRAINT `fk_week_day` FOREIGN KEY (`day`) REFERENCES `week_days` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `fk_venue_schedule` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
