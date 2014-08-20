DROP TABLE IF EXISTS `week_days`;

CREATE TABLE `week_days` (
	`id` INT(11) NOT NULL,
	`name` VARCHAR(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

INSERT INTO `week_days` (`id`, `name`) VALUES (0, 'others');
INSERT INTO `week_days` (`id`, `name`) VALUES (1, 'Monday');
INSERT INTO `week_days` (`id`, `name`) VALUES (2, 'Tuesday');
INSERT INTO `week_days` (`id`, `name`) VALUES (3, 'Wednesday');
INSERT INTO `week_days` (`id`, `name`) VALUES (4, 'Thursday');
INSERT INTO `week_days` (`id`, `name`) VALUES (5, 'Friday');
INSERT INTO `week_days` (`id`, `name`) VALUES (6, 'Saturday');
INSERT INTO `week_days` (`id`, `name`) VALUES (7, 'Sunday');
