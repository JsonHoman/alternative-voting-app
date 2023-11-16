DROP SCHEMA IF EXISTS `alternative-voting-app`;

CREATE SCHEMA `alternative-voting-app`;

USE `alternative-voting-app`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `election` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `election_type` VARCHAR(255),
    `start_date` VARCHAR(255),
    `end_date` VARCHAR(255),
    `round` INT,
    `ballot_id` INT,
    CONSTRAINT `FK_ballot_in_election` FOREIGN KEY (`ballot_id`) REFERENCES `ballot` (`id`),
    UNIQUE KEY `UK_ballot_in_election` (`ballot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `election_voters` (
    `election_id` INT,
    `voter_id` INT,
    PRIMARY KEY (`election_id`, `voter_id`),
    CONSTRAINT `FK_election_in_election_voters` FOREIGN KEY (`election_id`) REFERENCES `election` (`id`),
    CONSTRAINT `FK_voter_in_election_voters` FOREIGN KEY (`voter_id`) REFERENCES `voter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ballot` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `submission_date` VARCHAR(255),
    `election_id` INT,
    CONSTRAINT `FK_election_in_ballot` FOREIGN KEY (`election_id`) REFERENCES `election` (`id`),
    UNIQUE KEY `UK_election_in_ballot` (`election_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ballot_selection` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(255),
    `description` VARCHAR(255),
    `type` VARCHAR(255),
    `ballot_id` INT,
    CONSTRAINT `FK_ballot_in_ballot_selection` FOREIGN KEY (`ballot_id`) REFERENCES `ballot` (`id`),
    UNIQUE KEY `UK_ballot_in_ballot_selection` (`ballot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `person_info` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `sex` VARCHAR(255),
    `ethnicity` VARCHAR(255),
    `occupation` VARCHAR(255),
    `placeOfBirth` VARCHAR(255),
    `maritalStatus` VARCHAR(255),
    `veteranStatus` VARCHAR(255)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `candidate` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),
    `email` VARCHAR(255),
    `phone_number` VARCHAR(255),
    `address` VARCHAR(255),
    `date_of_birth` VARCHAR(255),
    `candidate_id` INT,
    `ballot_selection_id` INT,
    `person_info_id` INT,
    CONSTRAINT `FK_ballot_selection_in_candidate` FOREIGN KEY (`ballot_selection_id`) REFERENCES `ballot_selection` (`id`),
    CONSTRAINT `FK_person_info_in_candidate` FOREIGN KEY (`person_info_id`) REFERENCES `person_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `issue` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(255),
    `description` VARCHAR(255),
    `ballot_selection_id` INT,
    CONSTRAINT `FK_ballot_selection_in_issue` FOREIGN KEY (`ballot_selection_id`) REFERENCES `ballot_selection` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `vote` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `issue_id` INT,
    `candidate_id` INT,
    CONSTRAINT `FK_issue_in_vote` FOREIGN KEY (`issue_id`) REFERENCES `issue` (`id`),
    CONSTRAINT `FK_candidate_in_vote` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `voter` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),
    `email` VARCHAR(255),
    `phone_number` VARCHAR(255),
    `address` VARCHAR(255),
    `date_of_birth` VARCHAR(255),
    `person_info_id` INT,
    CONSTRAINT `FK_person_info_voter` FOREIGN KEY (`person_info_id`) REFERENCES `person_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `election_voter` (
    `voter_id` INT,
    `election_id` INT,
    PRIMARY KEY (`voter_id`, `election_id`),
    CONSTRAINT `FK_voter_in_election_voter` FOREIGN KEY (`voter_id`) REFERENCES `voter` (`id`),
    CONSTRAINT `FK_election_in_election_voter` FOREIGN KEY (`election_id`) REFERENCES `election` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;