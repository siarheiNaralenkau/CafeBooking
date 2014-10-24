CREATE TABLE `users` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`venue_id` INT(11) NOT NULL DEFAULT '0',
	`username` VARCHAR(50) NOT NULL,
	`password` VARCHAR(20) NOT NULL,
	`bonus_scores` INT(11) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`),
	INDEX `fk_u_venue` (`venue_id`),
	CONSTRAINT `fk_u_venue` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
