-- MySQL Script generated by MySQL Workbench
-- Fri Feb  2 20:12:51 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema play
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `play`;

-- -----------------------------------------------------
-- Schema play
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `play` DEFAULT CHARACTER SET utf8 ;
USE `play` ;

-- -----------------------------------------------------
-- Table `play`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `play`.`role` ;

CREATE TABLE IF NOT EXISTS `play`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `play`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `play`.`user` ;

CREATE TABLE IF NOT EXISTS `play`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `role_id` INT NOT NULL,
  `is_banned` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role1_idx` (`role_id` ASC),
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `play`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `play`.`parent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `play`.`parent` ;

CREATE TABLE IF NOT EXISTS `play`.`parent` (
  `user_id` INT NOT NULL,
  `wallet_points` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  `street` VARCHAR(255) NOT NULL,
  `street_number` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `photo_link` VARCHAR(255) NULL,
  `latitude` DECIMAL(9,6) NOT NULL,
  `longtitude` DECIMAL(9,6) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_parent_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `play`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `play`.`provider`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `play`.`provider` ;

CREATE TABLE IF NOT EXISTS `play`.`provider` (
  `user_id` INT NOT NULL,
  `company_name` VARCHAR(255) NOT NULL,
  `Description` TEXT(500) NULL,
  `AFM` VARCHAR(9) NOT NULL,
  `is_approved` TINYINT(1) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  `street` VARCHAR(255) NOT NULL,
  `street_number` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `photo_link` VARCHAR(255) NULL,
  `subscription_expiry_date` DATE NULL DEFAULT NULL,
  `registration_date` DATE NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_provider_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `play`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `play`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `play`.`category` ;

CREATE TABLE IF NOT EXISTS `play`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `play`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `play`.`event` ;

CREATE TABLE IF NOT EXISTS `play`.`event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `provider_user_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `tags` VARCHAR(255) NOT NULL,
  `age_from` INT NOT NULL,
  `age_to` INT NOT NULL,
  `latitude` DECIMAL(9,6) NOT NULL,
  `longtitude` DECIMAL(9,6) NOT NULL,
  `postal_code` VARCHAR(45) NULL,
  `street` VARCHAR(255) NULL,
  `street_number` VARCHAR(45) NULL,
  `date_time` DATETIME NOT NULL,
  `ticket_price` DECIMAL(5,2) NOT NULL,
  `available_tickets` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_category1_idx` (`category_id` ASC),
  INDEX `fk_event_provider1_idx` (`provider_user_id` ASC),
  CONSTRAINT `fk_event_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `play`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_provider1`
    FOREIGN KEY (`provider_user_id`)
    REFERENCES `play`.`provider` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `play`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `play`.`ticket` ;

CREATE TABLE IF NOT EXISTS `play`.`ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `parent_user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `date_time` DATETIME NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ticket_event1_idx` (`event_id` ASC),
  INDEX `fk_ticket_parent1_idx` (`parent_user_id` ASC),
  CONSTRAINT `fk_ticket_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `play`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_parent1`
    FOREIGN KEY (`parent_user_id`)
    REFERENCES `play`.`parent` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `play`.`event_photos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `play`.`event_photos` ;

CREATE TABLE IF NOT EXISTS `play`.`event_photos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
  `link` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_event_photos_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `play`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `play`.`report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `play`.`report` ;

CREATE TABLE IF NOT EXISTS `play`.`report` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `parent_user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `date_time` DATETIME NOT NULL,
  `comments` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_report_event1_idx` (`event_id` ASC),
  INDEX `fk_report_parent1_idx` (`parent_user_id` ASC),
  CONSTRAINT `fk_report_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `play`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_report_parent1`
    FOREIGN KEY (`parent_user_id`)
    REFERENCES `play`.`parent` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `play`.`administrator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `play`.`administrator` ;

CREATE TABLE IF NOT EXISTS `play`.`administrator` (
  `user_id` INT NOT NULL,
  `can_approve` TINYINT(1) NOT NULL,
  `can_alter_rights` TINYINT(1) NOT NULL,
  `can_alter_roles` TINYINT(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_administrator_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `play`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO role( name)
  VALUES ('administrator');
INSERT INTO role( name)
  VALUES ('parent');
INSERT INTO role( name)
  VALUES ('provider');
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
