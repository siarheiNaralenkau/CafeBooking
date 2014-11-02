CREATE TABLE `reviews` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`venue_id` INT(11) NOT NULL,
	`mark_food` DECIMAL(2,0) NULL DEFAULT NULL,
	`mark_service` DECIMAL(2,0) NULL DEFAULT NULL,
	`mark_atmosphere` DECIMAL(2,0) NULL DEFAULT NULL,
	`mark_price_quality` DECIMAL(2,0) NULL DEFAULT NULL,
	`comments_good` VARCHAR(200) NULL DEFAULT NULL,
	`comments_bad` VARCHAR(200) NULL DEFAULT NULL,
	`user_id` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `fk_reviews_venue` (`venue_id`),
	INDEX `fk_reviews_user` (`user_id`),
	CONSTRAINT `fk_reviews_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `fk_reviews_venue` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
