CREATE TABLE `admin_devices` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`venue_id` INT(11) NOT NULL,
	`registration_id` VARCHAR(250) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `fk_ad_venue` (`venue_id`),
	CONSTRAINT `fk_ad_venue` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;