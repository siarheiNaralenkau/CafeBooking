CREATE TABLE `bookings` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`venue_id` INT(11) NOT NULL DEFAULT '0',
	`visitor_contact_name` VARCHAR(100) NOT NULL DEFAULT '0',
	`visitor_contact_phone` VARCHAR(30) NOT NULL DEFAULT '0',
	`booking_time` DATETIME NULL DEFAULT NULL,
	`places_amount` TINYINT(4) NOT NULL DEFAULT '0',
	`status` TINYINT(4) NOT NULL DEFAULT '0',
	`notes` VARCHAR(300) NULL DEFAULT '0',
	PRIMARY KEY (`id`),
	INDEX `fk_venue` (`venue_id`),
	INDEX `fk_status` (`status`),
	CONSTRAINT `fk_status` FOREIGN KEY (`status`) REFERENCES `booking_status` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `fk_venue` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
