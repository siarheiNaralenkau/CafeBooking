CREATE TABLE `week_days` (
	`id` INT(11) NOT NULL,
	`name` VARCHAR(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

INSERT INTO `week_days` (`id`, `name`) VALUES (0, 'others');
INSERT INTO `week_days` (`id`, `name`) VALUES (1, '�����������');
INSERT INTO `week_days` (`id`, `name`) VALUES (2, '�������');
INSERT INTO `week_days` (`id`, `name`) VALUES (3, '�����');
INSERT INTO `week_days` (`id`, `name`) VALUES (4, '�������');
INSERT INTO `week_days` (`id`, `name`) VALUES (5, '�������');
INSERT INTO `week_days` (`id`, `name`) VALUES (6, '�������');
INSERT INTO `week_days` (`id`, `name`) VALUES (7, '�����������');
