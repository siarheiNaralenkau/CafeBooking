CREATE TABLE `week_days` (
	`id` INT(11) NOT NULL,
	`name` VARCHAR(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
ENGINE=InnoDB;

INSERT INTO week_days VALUES(1, 'Понедельник');
INSERT INTO week_days VALUES(2, 'Вторник');
INSERT INTO week_days VALUES(3, 'Среда');
INSERT INTO week_days VALUES(4, 'Четверг');
INSERT INTO week_days VALUES(5, 'Пятница');
INSERT INTO week_days VALUES(6, 'Суббота');
INSERT INTO week_days VALUES(7, 'Воскресенье');
