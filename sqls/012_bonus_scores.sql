CREATE TABLE `bonus_scores` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_id` INT(11) NOT NULL DEFAULT '0',
	`venue_id` INT(11) NOT NULL DEFAULT '0',
	`scores` INT(11) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`),
	CONSTRAINT `fk_scores_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `fk_scores_venues` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
