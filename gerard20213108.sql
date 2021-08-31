CREATE DATABASE  IF NOT EXISTS `gerard` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `gerard`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: gerard
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `app_user` (
  `app_user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` char(72) DEFAULT NULL,
  `name` varchar(250) NOT NULL,
  `surname` varchar(250) DEFAULT NULL,
  `patronymic` varchar(250) DEFAULT NULL,
  `phone` varchar(9) NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`app_user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'sidelnikova.liliya@gmail.com','$2a$10$77sJj.5Wra8TP97rZqiHw.8wMQcbv6sYGeUFagf6O98B5ozj1z.UO','billy','milly','','257805405',1),(2,'johnny890legend@gmail.com','','John','Legend',NULL,'291238903',0),(3,'spearsb149@gmail.com','','Иванова','Бритни','','290000000',0),(4,'peter7389griffin@gmail.com','','Peter','Griffin',NULL,'445667989',0),(5,'koza.dereza@gmail.com','$2a$10$Z.syeCxyh9lY.uvtxqFLaOitkUy4AO7cSTvMdVizgiUKt8QyPEH9K','Liza','Kudrow','Viktarauna','334568454',1),(7,'sidelnikova.liliya2@gmail.com',NULL,'Лилия','Сидельникова','Сергеевна','29888888',0),(16,'sidelnikova.liliya3@gmail.com',NULL,'Лилия','Сидельникова','Сергеевна','29888888',0),(21,'sidelnikova.liliya2.@gmail.com',NULL,'Лилия','Сидельникова','','290000000',0),(26,'valentina.sobchak@gmail.com',NULL,'Valentina','Sobchak','Petrovna','441457896',0),(35,'billy.joel@gmail.com',NULL,'big','billy','','257805405',0),(41,'big.brother@gmail.com',NULL,'nameghjgh','joel','','293060661',0),(43,'lolita.milyavckaya@gmail.com',NULL,'lolita','milly','','297841651',0),(45,'billy.white@gmail.com',NULL,'big','billy','','293060062',0),(87,'vechni.sputnik@mail.ru',NULL,'Sputnik','Vechni','','290000000',0),(88,'gorilla.mobilla@mail.ru',NULL,'ElizaBETH','КороЛева','','257845151',0);
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dog`
--

DROP TABLE IF EXISTS `dog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dog` (
  `dog_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dog_sex` enum('male','female') NOT NULL,
  `nickname` varchar(30) NOT NULL,
  `fullname` varchar(45) NOT NULL,
  `birthday` date NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `active` tinyint(1) unsigned zerofill NOT NULL DEFAULT '1',
  `avatar_photo_id` int(10) unsigned NOT NULL DEFAULT '23',
  `pedigree_photo_id` int(10) unsigned NOT NULL DEFAULT '24',
  PRIMARY KEY (`dog_id`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`),
  UNIQUE KEY `fullname_UNIQUE` (`fullname`),
  KEY `fk_avatar_photo_id_idx` (`avatar_photo_id`),
  KEY `fk_pedigree_photo_id_idx` (`pedigree_photo_id`),
  CONSTRAINT `fk_avatar_photo_id` FOREIGN KEY (`avatar_photo_id`) REFERENCES `photo` (`photo_id`),
  CONSTRAINT `fk_pedigree_photo_id` FOREIGN KEY (`pedigree_photo_id`) REFERENCES `photo` (`photo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dog`
--

LOCK TABLES `dog` WRITE;
/*!40000 ALTER TABLE `dog` DISABLE KEYS */;
INSERT INTO `dog` VALUES (1,'female','Angel','Quen of Love Sweet Sixteen','2016-04-10',NULL,0,1,5),(2,'male','Stanlye','Kind King of Sunhine 1','2015-08-23',NULL,0,2,6),(3,'male','Bobby','Robert Child of Kindess 1','2021-04-12',NULL,0,3,7),(4,'female','Didi','Diane Child of Kindess 2','2021-04-12',NULL,1,4,8),(5,'male','Gerard','Amulet Uspeha Gerard','2020-04-01',NULL,1,9,10),(6,'male','Albert','Albert Forest Prince 1','2019-03-03',NULL,1,23,24),(7,'female','Rozalinda','Rozalinda Sky Queen','2018-03-04',NULL,1,23,24),(8,'male','Victor','Victor Forest Prince 2','2020-03-05',NULL,1,23,24);
/*!40000 ALTER TABLE `dog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `photo` (
  `photo_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `photo_path` varchar(200) NOT NULL,
  `dog_id` int(10) unsigned DEFAULT NULL,
  `photo_date` date NOT NULL,
  `photo_type` enum('photo','pedigree','avatar') NOT NULL DEFAULT 'photo',
  PRIMARY KEY (`photo_id`),
  UNIQUE KEY `path_UNIQUE` (`photo_path`),
  KEY `fk_dog_id_idx` (`dog_id`),
  CONSTRAINT `fk_dog_id4` FOREIGN KEY (`dog_id`) REFERENCES `dog` (`dog_id`) ON DELETE CASCADE ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo`
--

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
INSERT INTO `photo` VALUES (1,'img/avatars/avatar1.jpg',1,'2020-08-10','avatar'),(2,'img/avatars/avatar2.jpg',2,'2019-10-01','avatar'),(3,'img/avatars/avatar3.jpg',3,'2019-10-01','avatar'),(4,'img/avatars/avatar4.jpg',4,'2019-10-01','avatar'),(5,'img/pedigrees/pedigree1.jpg',1,'2018-07-18','pedigree'),(6,'img/pedigrees/pedigree2.jpg',2,'2021-05-04','pedigree'),(7,'img/pedigrees/pedigree3.jpg',3,'2021-05-04','pedigree'),(8,'img/pedigrees/pedigree4.jpg',4,'2021-05-04','pedigree'),(9,'img/avatars/avatar5.jpg',5,'2018-07-18','avatar'),(10,'img/pedigrees/pedigree5.jpg',5,'2020-08-11','pedigree'),(11,'img/photos/photo5_20210827154825.jpg',5,'2021-07-18','photo'),(12,'img/photos/photo5_20210827154826.jpg',5,'2021-07-18','photo'),(13,'img/photos/photo5_20210827154827.jpg',5,'2021-07-18','photo'),(14,'img/photos/photo5_20210827154829.jpg',5,'2021-07-18','photo'),(15,'img/photos/photo5_20210827154830.jpg',5,'2021-07-18','photo'),(16,'img/photos/photo5_20210827154832.jpg',5,'2021-07-18','photo'),(23,'img/avatars/avatar_default.png',NULL,'2021-08-27','avatar'),(24,'img/pedigrees/pedigree_default.png',NULL,'2021-08-27','pedigree'),(59,'img/photos/photo5_20210827154833.jpg',5,'2021-08-30','photo'),(62,'img/photos/photo5_20210827154834.jpg',5,'2021-08-31','photo'),(63,'img/photos/photo5_20210827154835.jpg',5,'2021-08-31','photo'),(64,'img/photos/photo5_20210827154836.jpg',5,'2021-08-31','photo'),(65,'img/photos/photo5_20210827154837.jpg',5,'2021-08-31','photo'),(66,'img/photos/photo5_20210827154838.jpg',5,'2021-08-31','photo'),(67,'img/photos/photo5_20210827154840.jpg',5,'2021-08-31','photo'),(68,'img/photos/photo5_20210827154842.jpg',5,'2021-08-31','photo');
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `set_photo_date` BEFORE INSERT ON `photo` FOR EACH ROW BEGIN
		if( isnull(new.photo_date)) then
        set new.photo_date = curdate();
        
        end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `request` (
  `request_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `request_status` enum('pending','accepted','rejected') NOT NULL DEFAULT 'pending',
  `request_type` enum('puppy') NOT NULL DEFAULT 'puppy',
  `content` varchar(450) NOT NULL,
  `date_fact` date NOT NULL,
  `reply` varchar(450) DEFAULT NULL,
  `dog_id` int(10) unsigned NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`request_id`),
  KEY `fk_dog_id2` (`dog_id`),
  KEY `fk_unq_email` (`email`),
  CONSTRAINT `fk_dog_id2` FOREIGN KEY (`dog_id`) REFERENCES `dog` (`dog_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_unq_email` FOREIGN KEY (`email`) REFERENCES `app_user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (67,'accepted','puppy','Запрос на питомца.','2021-08-12','Your request was accepted. Please contact us by our mobile phone.',1,'vechni.sputnik@mail.ru'),(69,'pending','puppy','Hello , this is Queen Elizabeth II. Nice dogs','2021-08-31',NULL,3,'gorilla.mobilla@mail.ru'),(70,'rejected','puppy','Test request 134','2021-08-31','Sorry. Your request was rejected now.\nWe put it to the end ot the queue. May be later we accept it ',3,'gorilla.mobilla@mail.ru'),(71,'pending','puppy','Hello this my request','2021-08-31',NULL,3,'gorilla.mobilla@mail.ru'),(72,'accepted','puppy','Nice, i like it','2021-08-31','Your request was accepted. Please contact us by our mobile phone.',3,'gorilla.mobilla@mail.ru'),(73,'accepted','puppy','Запрос на питомца.','2021-08-12','Your request was accepted. Please contact us by our mobile phone.',1,'vechni.sputnik@mail.ru'),(78,'pending','puppy','Запрос на питомца.','2021-08-12',NULL,1,'vechni.sputnik@mail.ru');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `set_request_photo_date` BEFORE INSERT ON `request` FOR EACH ROW BEGIN
	if(isnull(new.date_fact)) 
	then 
    set new.date_fact = current_date();
    end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-31  5:12:31
