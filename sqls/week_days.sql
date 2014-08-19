CREATE TABLE `week_days` (
	`id` INT(11) NOT NULL,
	`name` VARCHAR(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

INSERT INTO `week_days` (`id`, `name`) VALUES (0, 'others');
INSERT INTO `week_days` (`id`, `name`) VALUES (1, 'Понедельник');
INSERT INTO `week_days` (`id`, `name`) VALUES (2, 'Вторник');
INSERT INTO `week_days` (`id`, `name`) VALUES (3, 'Среда');
INSERT INTO `week_days` (`id`, `name`) VALUES (4, 'Четверг');
INSERT INTO `week_days` (`id`, `name`) VALUES (5, 'Пятница');
INSERT INTO `week_days` (`id`, `name`) VALUES (6, 'Суббота');
INSERT INTO `week_days` (`id`, `name`) VALUES (7, 'Воскресенье');
