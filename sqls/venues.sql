CREATE TABLE `venues` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`unique_id` VARCHAR(35) NULL DEFAULT NULL,
	`name` VARCHAR(100) NULL DEFAULT NULL,
	`phone` VARCHAR(25) NULL DEFAULT NULL,
	`address` VARCHAR(100) NULL DEFAULT NULL,
	`city` VARCHAR(40) NULL DEFAULT NULL,
	`country` VARCHAR(60) NULL DEFAULT NULL,
	`latitude` FLOAT NULL DEFAULT NULL,
	`longitude` FLOAT NULL DEFAULT NULL,
	`category` VARCHAR(60) NULL DEFAULT NULL,
	`has_free_seats` TINYINT(1) NULL DEFAULT NULL,
	`admin_user` VARCHAR(100) NULL DEFAULT NULL,
	`in_booking_system` TINYINT(1) NOT NULL DEFAULT '0',
	`tables_amount` INT(11) NULL DEFAULT NULL,
	`icon_url` VARCHAR(75) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `unique_id` (`unique_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;