CREATE TABLE `users` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	`surname` VARCHAR(50) NOT NULL,
	`email` VARCHAR(50) NOT NULL,
	`phone` VARCHAR(20) NOT NULL,
	`password` VARCHAR(20) NOT NULL,
	`bonus_scores` INT(11) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
