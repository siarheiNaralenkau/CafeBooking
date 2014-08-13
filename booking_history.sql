CREATE TABLE `booking_history` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`booking_id` INT(11) NOT NULL DEFAULT '0',
	`new_status` TINYINT(4) NOT NULL DEFAULT '0',
	`action_user` VARCHAR(100) NOT NULL DEFAULT '0',
	`change_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `fk_booking` (`booking_id`),
	INDEX `fk_status_history` (`new_status`),
	CONSTRAINT `fk_booking` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `fk_status_history` FOREIGN KEY (`new_status`) REFERENCES `booking_status` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4;
