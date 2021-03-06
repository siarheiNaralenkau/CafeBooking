CREATE TABLE `venues` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`rating` DECIMAL(4,2) NULL DEFAULT '0.00',
	`free_tables_amount` INT(11) NULL DEFAULT '0',
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
	`in_booking_system` TINYINT(1) NOT NULL DEFAULT '0',
	`tables_amount` INT(11) NULL DEFAULT NULL,
	`icon_url` VARCHAR(150) NULL DEFAULT NULL,
	`open_time` VARCHAR(10) NULL DEFAULT '10:00',
	`close_time` VARCHAR(10) NULL DEFAULT '24:00',
	`plan` TEXT NULL,
	`cuisine` VARCHAR(50) NULL DEFAULT NULL,
	`has_wifi` TINYINT(1) NULL DEFAULT NULL,
	`has_outdoors_seats` TINYINT(1) NULL DEFAULT NULL,
	`take_credit_cards` TINYINT(1) NULL DEFAULT '0',
	`admin_password` VARCHAR(20) NULL DEFAULT 'admin',
	`admin_user` VARCHAR(100) NULL DEFAULT 'admin',
	`avg_check` VARCHAR(30) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `unique_id` (`unique_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;