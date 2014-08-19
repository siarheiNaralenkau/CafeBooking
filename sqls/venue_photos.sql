CREATE TABLE `venue_photos` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`uid` VARCHAR(35) NOT NULL DEFAULT '0',
	`venue_id` VARCHAR(35) NULL DEFAULT '0',
	`url` VARCHAR(100) NULL DEFAULT '0',
	PRIMARY KEY (`id`),
	INDEX `fk_venue_uid` (`venue_id`),
	CONSTRAINT `fk_venue_uid` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`unique_id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;