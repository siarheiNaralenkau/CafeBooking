CREATE TABLE `bookings` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`venue_id` INT(11) NOT NULL DEFAULT '0',
	`visitor_contact_name` VARCHAR(100) NULL DEFAULT NULL,
	`user_id` INT(11) NULL DEFAULT NULL,
	`visitor_contact_phone` VARCHAR(30) NULL DEFAULT NULL,
	`spent_money` INT(11) NULL DEFAULT NULL,
	`booking_time` TIMESTAMP NULL DEFAULT NULL,
	`places_amount` TINYINT(4) NOT NULL DEFAULT '0',
	`status` TINYINT(4) NOT NULL DEFAULT '0',
	`notes` VARCHAR(300) NULL DEFAULT '0',
	`booking_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`table_no` VARCHAR(30) NULL DEFAULT NULL,
	`visitor_spent_money` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `fk_venue` (`venue_id`),
	INDEX `fk_status` (`status`),
	INDEX `fk_booking_user` (`user_id`),
	CONSTRAINT `fk_booking_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `fk_status` FOREIGN KEY (`status`) REFERENCES `booking_status` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `fk_venue` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COMMENT='ALTER TABLE bookings ADD COLUMN `booking_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;