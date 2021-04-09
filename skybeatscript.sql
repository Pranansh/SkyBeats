-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SkyBeats
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SkyBeats
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SkyBeats` DEFAULT CHARACTER SET utf8 ;
USE `SkyBeats` ;

-- -----------------------------------------------------
-- Table `SkyBeats`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`customer` (
  `customerID` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`customerID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkyBeats`.`manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`manager` (
  `managerID` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `views` INT UNSIGNED NULL,
  `earnings` DECIMAL(10,2) UNSIGNED NULL,
  `numberofproducer` INT UNSIGNED NULL,
  PRIMARY KEY (`managerID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkyBeats`.`producer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`producer` (
  `producerID` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `views` INT UNSIGNED NULL,
  `earning` DECIMAL(10,2) UNSIGNED NULL,
  `manager_managerID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`producerID`),
  INDEX `fk_producer_manager1_idx` (`manager_managerID` ASC) VISIBLE,
  CONSTRAINT `fk_producer_manager1`
    FOREIGN KEY (`manager_managerID`)
    REFERENCES `SkyBeats`.`manager` (`managerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkyBeats`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`employee` (
  `employeeID` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `salary` DECIMAL(10,2) UNSIGNED NULL,
  PRIMARY KEY (`employeeID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkyBeats`.`adcompany`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`adcompany` (
  `adID` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `funds` DECIMAL(10,2) UNSIGNED NULL,
  PRIMARY KEY (`adID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkyBeats`.`songs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`songs` (
  `songID` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `rating` DECIMAL(10,2) UNSIGNED NULL,
  `numberofcustomersrated` INT UNSIGNED NULL,
  `views` INT UNSIGNED NULL,
  `producer_producerID` VARCHAR(45) NOT NULL,
  `adcompany_adID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`songID`),
  INDEX `fk_songs_producer1_idx` (`producer_producerID` ASC) VISIBLE,
  INDEX `fk_songs_adcompany1_idx` (`adcompany_adID` ASC) VISIBLE,
  CONSTRAINT `fk_songs_producer1`
    FOREIGN KEY (`producer_producerID`)
    REFERENCES `SkyBeats`.`producer` (`producerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_songs_adcompany1`
    FOREIGN KEY (`adcompany_adID`)
    REFERENCES `SkyBeats`.`adcompany` (`adID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkyBeats`.`playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`playlist` (
  `playlistID` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `noofsongs` INT UNSIGNED NULL,
  `customer_customerID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`playlistID`),
  INDEX `fk_playlist_customer1_idx` (`customer_customerID` ASC) VISIBLE,
  CONSTRAINT `fk_playlist_customer1`
    FOREIGN KEY (`customer_customerID`)
    REFERENCES `SkyBeats`.`customer` (`customerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkyBeats`.`livestream`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`livestream` (
  `livestreamID` VARCHAR(45) NOT NULL,
  `dateandtime` DATETIME NULL,
  `noofattendees` INT UNSIGNED NULL,
  `producer_producerID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`livestreamID`, `producer_producerID`),
  INDEX `fk_livestream_producer1_idx` (`producer_producerID` ASC) VISIBLE,
  CONSTRAINT `fk_livestream_producer1`
    FOREIGN KEY (`producer_producerID`)
    REFERENCES `SkyBeats`.`producer` (`producerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkyBeats`.`history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`history` (
  `songID` VARCHAR(45) NOT NULL,
  `dateandtime` DATETIME NOT NULL,
  `customer_customerID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`dateandtime`, `customer_customerID`),
  INDEX `fk_history_customer_idx` (`customer_customerID` ASC) VISIBLE,
  CONSTRAINT `fk_history_customer`
    FOREIGN KEY (`customer_customerID`)
    REFERENCES `SkyBeats`.`customer` (`customerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkyBeats`.`complain`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`complain` (
  `complainID` VARCHAR(45) NOT NULL,
  `reason` VARCHAR(45) NOT NULL,
  `customer_customerID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`complainID`, `customer_customerID`),
  INDEX `fk_complain_customer1_idx` (`customer_customerID` ASC) VISIBLE,
  CONSTRAINT `fk_complain_customer1`
    FOREIGN KEY (`customer_customerID`)
    REFERENCES `SkyBeats`.`customer` (`customerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkyBeats`.`listens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`listens` (
  `customer_customerID` VARCHAR(45) NOT NULL,
  `songs_songID` VARCHAR(45) NOT NULL,
  `dateandtime` DATETIME NOT NULL,
  PRIMARY KEY (`customer_customerID`, `songs_songID`, `dateandtime`),
  INDEX `fk_customer_has_songs_songs1_idx` (`songs_songID` ASC) VISIBLE,
  INDEX `fk_customer_has_songs_customer1_idx` (`customer_customerID` ASC) VISIBLE,
  CONSTRAINT `fk_customer_has_songs_customer1`
    FOREIGN KEY (`customer_customerID`)
    REFERENCES `SkyBeats`.`customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_has_songs_songs1`
    FOREIGN KEY (`songs_songID`)
    REFERENCES `SkyBeats`.`songs` (`songID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkyBeats`.`views`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`views` (
  `customer_customerID` VARCHAR(45) NOT NULL,
  `livestream_livestreamID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`customer_customerID`, `livestream_livestreamID`),
  INDEX `fk_customer_has_livestream_livestream1_idx` (`livestream_livestreamID` ASC) VISIBLE,
  INDEX `fk_customer_has_livestream_customer1_idx` (`customer_customerID` ASC) VISIBLE,
  CONSTRAINT `fk_customer_has_livestream_customer1`
    FOREIGN KEY (`customer_customerID`)
    REFERENCES `SkyBeats`.`customer` (`customerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_customer_has_livestream_livestream1`
    FOREIGN KEY (`livestream_livestreamID`)
    REFERENCES `SkyBeats`.`livestream` (`livestreamID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkyBeats`.`process`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`process` (
  `complain_complainID` VARCHAR(45) NOT NULL,
  `complain_customer_customerID` VARCHAR(45) NOT NULL,
  `employee_employeeID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`complain_complainID`, `complain_customer_customerID`, `employee_employeeID`),
  INDEX `fk_complain_has_employee_employee1_idx` (`employee_employeeID` ASC) VISIBLE,
  INDEX `fk_complain_has_employee_complain1_idx` (`complain_complainID` ASC, `complain_customer_customerID` ASC) VISIBLE,
  CONSTRAINT `fk_complain_has_employee_complain1`
    FOREIGN KEY (`complain_complainID` , `complain_customer_customerID`)
    REFERENCES `SkyBeats`.`complain` (`complainID` , `customer_customerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_complain_has_employee_employee1`
    FOREIGN KEY (`employee_employeeID`)
    REFERENCES `SkyBeats`.`employee` (`employeeID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SkyBeats`.`contains`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SkyBeats`.`contains` (
  `playlist_playlistID` VARCHAR(45) NOT NULL,
  `songs_songID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`playlist_playlistID`, `songs_songID`),
  INDEX `fk_playlist_has_songs_songs1_idx` (`songs_songID` ASC) VISIBLE,
  INDEX `fk_playlist_has_songs_playlist1_idx` (`playlist_playlistID` ASC) VISIBLE,
  CONSTRAINT `fk_playlist_has_songs_playlist1`
    FOREIGN KEY (`playlist_playlistID`)
    REFERENCES `SkyBeats`.`playlist` (`playlistID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_playlist_has_songs_songs1`
    FOREIGN KEY (`songs_songID`)
    REFERENCES `SkyBeats`.`songs` (`songID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
