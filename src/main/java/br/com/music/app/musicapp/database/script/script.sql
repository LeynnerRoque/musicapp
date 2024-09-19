-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema music_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema music_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `music_db` DEFAULT CHARACTER SET utf8 ;
USE `music_db` ;

-- -----------------------------------------------------
-- Table `music_db`.`record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_db`.`record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `music_db`.`artists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_db`.`artists` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `type` VARCHAR(50) NULL,
  `date_create` DATE NULL,
  `origin` VARCHAR(100) NULL,
  `record_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_artists_record1_idx` (`record_id` ASC) VISIBLE,
  CONSTRAINT `fk_artists_record1`
    FOREIGN KEY (`record_id`)
    REFERENCES `music_db`.`record` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `music_db`.`style`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_db`.`style` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name_style` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `music_db`.`albuns`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_db`.`albuns` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `style_id` INT NOT NULL,
  `artists_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_albuns_style_idx` (`style_id` ASC) VISIBLE,
  INDEX `fk_albuns_artists1_idx` (`artists_id` ASC) VISIBLE,
  CONSTRAINT `fk_albuns_style`
    FOREIGN KEY (`style_id`)
    REFERENCES `music_db`.`style` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_albuns_artists1`
    FOREIGN KEY (`artists_id`)
    REFERENCES `music_db`.`artists` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
