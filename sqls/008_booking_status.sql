DROP TABLE IF EXISTS `booking_status`;

CREATE TABLE `booking_status` (
	`id` TINYINT(4) NOT NULL,
	`status` VARCHAR(10) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

INSERT INTO `booking_status` (`id`, `status`) VALUES (1, 'pending');
INSERT INTO `booking_status` (`id`, `status`) VALUES (2, 'approved');
INSERT INTO `booking_status` (`id`, `status`) VALUES (3, 'cancelled');
INSERT INTO `booking_status` (`id`, `status`) VALUES (4, 'rejected');
INSERT INTO `booking_status` (`id`, `status`) VALUES (5, 'deleted');
INSERT INTO `booking_status` (`id`, `status`) VALUES (6, 'closed');


