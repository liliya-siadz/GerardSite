-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema gerard
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gerard
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gerard` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `gerard` ;

-- -----------------------------------------------------
-- Table `gerard`.`achievement_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerard`.`achievement_type` (
  `achievement_type` VARCHAR(30) NOT NULL,
  `achivement_type_descr` VARCHAR(100) NOT NULL,
  `status_active` TINYINT UNSIGNED NOT NULL DEFAULT '1',
  PRIMARY KEY (`achievement_type`),
  UNIQUE INDEX `achievement_type_UNIQUE` (`achievement_type` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `gerard`.`app_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerard`.`app_user` (
  `app_user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `app_user_status` ENUM('active', 'non-confirmed', 'blocked', 'archived') NOT NULL,
  `app_user_role` ENUM('admin', 'user') NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(250) NOT NULL,
  `surname` VARCHAR(250) NOT NULL,
  `patronymic` VARCHAR(250) NULL DEFAULT NULL,
  `phone` INT UNSIGNED NOT NULL,
  `app_user_sex` ENUM('male', 'female', 'binary') NULL DEFAULT NULL,
  PRIMARY KEY (`app_user_id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `gerard`.`breeding`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerard`.`breeding` (
  `breeding_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `date_planned` DATE NOT NULL,
  `male_dog_id` INT UNSIGNED NOT NULL,
  `female_dog_id` INT UNSIGNED NOT NULL,
  `date_fact` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`breeding_id`),
  UNIQUE INDEX `unique_breeding_idx` (`date_planned` ASC, `male_dog_id` ASC, `female_dog_id` ASC) VISIBLE,
  INDEX `fk_male_dog_id_idx` (`male_dog_id` ASC) VISIBLE,
  INDEX `fk_female_dog_id_idx` (`female_dog_id` ASC) VISIBLE,
  CONSTRAINT `fk_female_dog_id`
    FOREIGN KEY (`female_dog_id`)
    REFERENCES `gerard`.`dog` (`dog_id`),
  CONSTRAINT `fk_male_dog_id`
    FOREIGN KEY (`male_dog_id`)
    REFERENCES `gerard`.`dog` (`dog_id`)
    ON DELETE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `gerard`.`request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerard`.`request` (
  `request_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `request_status` ENUM('pending', 'contact', 'rejected') NOT NULL,
  `request_type` ENUM('запрос на щенка', 'предварительный запрос на щенка') NOT NULL,
  `app_user_id` INT UNSIGNED NOT NULL,
  `breeding_id` INT UNSIGNED NOT NULL,
  `content` TEXT NOT NULL,
  `date_fact` DATE NOT NULL,
  `reply` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  INDEX `fk_app_user_id_idx` (`app_user_id` ASC) VISIBLE,
  INDEX `fk_breeding_id_idx` (`breeding_id` ASC) VISIBLE,
  CONSTRAINT `fk_app_user_id`
    FOREIGN KEY (`app_user_id`)
    REFERENCES `gerard`.`app_user` (`app_user_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_breeding_id`
    FOREIGN KEY (`breeding_id`)
    REFERENCES `gerard`.`breeding` (`breeding_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `gerard`.`media`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerard`.`media` (
  `media_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `path` VARCHAR(200) NOT NULL,
  `media_type` ENUM('photo', 'video', 'pedigree') NOT NULL DEFAULT 'photo',
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `dog_id` INT UNSIGNED NULL DEFAULT NULL,
  `request_id` INT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`media_id`),
  UNIQUE INDEX `path_UNIQUE` (`path` ASC) VISIBLE,
  INDEX `fk_dog_id_idx` (`dog_id` ASC) VISIBLE,
  INDEX `fk_request_id_idx` (`request_id` ASC) VISIBLE,
  CONSTRAINT `fk_dog_id3`
    FOREIGN KEY (`dog_id`)
    REFERENCES `gerard`.`dog` (`dog_id`)
    ON DELETE SET NULL,
  CONSTRAINT `fk_request_id`
    FOREIGN KEY (`request_id`)
    REFERENCES `gerard`.`request` (`request_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `gerard`.`dog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerard`.`dog` (
  `dog_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `dog_status` ENUM('exchibition', 'exchibition-fertile', 'non-active') NOT NULL,
  `dog_sex` ENUM('male', 'female') NOT NULL,
  `nickname` VARCHAR(20) NOT NULL,
  `fullname` VARCHAR(45) NOT NULL,
  `birthday` DATE NOT NULL,
  `mother_dog_id` INT UNSIGNED NULL DEFAULT NULL,
  `father_dog_id` INT UNSIGNED NULL DEFAULT NULL,
  `avatar_media_id` INT UNSIGNED NOT NULL,
  `pedigree_media_id` INT UNSIGNED NOT NULL,
  `home_kernnel` TINYINT NOT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`dog_id`),
  UNIQUE INDEX `pedigree_media_id_UNIQUE` (`pedigree_media_id` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE,
  UNIQUE INDEX `fullname_UNIQUE` (`fullname` ASC) VISIBLE,
  UNIQUE INDEX `avatar_media_id_UNIQUE` (`avatar_media_id` ASC) VISIBLE,
  INDEX `fk_mother_dog_idx` (`mother_dog_id` ASC) VISIBLE,
  INDEX `fk_father_dog_idx` (`father_dog_id` ASC) VISIBLE,
  INDEX `fk_avatar_media_id_idx` (`avatar_media_id` ASC) VISIBLE,
  INDEX `fx_pedigree_media_idx` (`pedigree_media_id` ASC) VISIBLE,
  CONSTRAINT `fk_avatar_media_id`
    FOREIGN KEY (`avatar_media_id`)
    REFERENCES `gerard`.`media` (`media_id`),
  CONSTRAINT `fk_father_dog_id`
    FOREIGN KEY (`father_dog_id`)
    REFERENCES `gerard`.`dog` (`dog_id`),
  CONSTRAINT `fk_mother_dog_id`
    FOREIGN KEY (`mother_dog_id`)
    REFERENCES `gerard`.`dog` (`dog_id`),
  CONSTRAINT `fk_pedigree_media_id`
    FOREIGN KEY (`pedigree_media_id`)
    REFERENCES `gerard`.`media` (`media_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `gerard`.`achievement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerard`.`achievement` (
  `achievement_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `achievement_type` VARCHAR(30) NOT NULL,
  `dog_id` INT UNSIGNED NOT NULL,
  `achievement_date` DATE NOT NULL,
  PRIMARY KEY (`achievement_id`),
  INDEX `fk_achievement_type_idx` (`achievement_type` ASC) VISIBLE,
  INDEX `fk_dog_id_idx1` (`dog_id` ASC) VISIBLE,
  CONSTRAINT `fk_achievement_type`
    FOREIGN KEY (`achievement_type`)
    REFERENCES `gerard`.`achievement_type` (`achievement_type`)
    ON UPDATE CASCADE,
  CONSTRAINT `fk_dog_id1`
    FOREIGN KEY (`dog_id`)
    REFERENCES `gerard`.`dog` (`dog_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `gerard`.`health_test_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerard`.`health_test_type` (
  `health_test_type` VARCHAR(40) NOT NULL,
  `health_test_type_descr` TEXT NOT NULL,
  `status_active` TINYINT UNSIGNED NOT NULL DEFAULT '1',
  PRIMARY KEY (`health_test_type`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `gerard`.`health_test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gerard`.`health_test` (
  `health_test_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `health_test_type` VARCHAR(40) NOT NULL,
  `dog_id` INT UNSIGNED NOT NULL,
  `date_fact` VARCHAR(45) NOT NULL,
  `result` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`health_test_id`),
  UNIQUE INDEX `unique_health_test_idx` (`health_test_type` ASC, `dog_id` ASC, `date_fact` ASC) VISIBLE,
  INDEX `fk_health_test_type_idx` (`health_test_type` ASC) VISIBLE,
  INDEX `fk_dog_id_idx` (`dog_id` ASC) VISIBLE,
  CONSTRAINT `fk_dog_id`
    FOREIGN KEY (`dog_id`)
    REFERENCES `gerard`.`dog` (`dog_id`),
  CONSTRAINT `fk_health_test_type`
    FOREIGN KEY (`health_test_type`)
    REFERENCES `gerard`.`health_test_type` (`health_test_type`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
