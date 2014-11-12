CREATE TABLE `bonus_history` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_id` INT(11) NULL DEFAULT NULL,
	`venue_id` INT(11) NULL DEFAULT NULL,
	`scores_change` INT(11) NOT NULL,
	`change_time` TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `fk_bonus_history_user` (`user_id`),
	INDEX `fk_bonus_history_venue` (`venue_id`),
	CONSTRAINT `fk_bonus_history_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `fk_bonus_history_venue` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
