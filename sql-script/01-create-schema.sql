DROP SCHEMA IF EXISTS `ultimate-task`;

CREATE SCHEMA `ultimate-task`;

USE `ultimate-task`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `student`;
DROP TABLE IF EXISTS `instructor`;
DROP TABLE IF EXISTS `instructor_student`;

CREATE TABLE `student` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Int - generated',
    `first_name` VARCHAR(60) NOT NULL,
    `last_name` VARCHAR(60) NOT NULL,
    `age` SMALLINT NOT NULL,
    `email` VARCHAR(100) NOT NULL COMMENT 'String - Email address',
    `field_of_study` VARCHAR(120) NOT NULL COMMENT 'String - Course of study (specialization)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `instructor` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Int - generated',
    `first_name` VARCHAR(60) NOT NULL,
    `last_name` VARCHAR(60) NOT NULL,
    `age` SMALLINT NOT NULL,
    `email` VARCHAR(100) NOT NULL COMMENT 'String - Email address',
    `subject` VARCHAR(120) NOT NULL COMMENT 'String - Subject taught by a instructor/professor',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `instructor_student` (
    `instructor_id` INT NOT NULL,
    `student_id` INT NOT NULL,

    PRIMARY KEY (`instructor_id`,`student_id`),
    KEY `FK_STUDENT_idx` (`student_id`),

    CONSTRAINT `FK_INSTRUCTOR` FOREIGN KEY (`instructor_id`)
    REFERENCES `instructor` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`)
    REFERENCES `student` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;