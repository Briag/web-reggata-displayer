CREATE DATABASE  IF NOT EXISTS `regatta` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `regatta`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: regatta
-- ------------------------------------------------------
-- Server version	5.6.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `boat`
--

DROP TABLE IF EXISTS `boat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boat` (
  `idBoat` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `photo` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`idBoat`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boat`
--

LOCK TABLES `boat` WRITE;
/*!40000 ALTER TABLE `boat` DISABLE KEYS */;
INSERT INTO `boat` VALUES (1,'Jumacabi',''),(2,'Goldfinger',NULL);
/*!40000 ALTER TABLE `boat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inscription` (
  `Regatta_idRegatta` int(11) NOT NULL,
  `Team_idTeam` int(11) NOT NULL,
  PRIMARY KEY (`Regatta_idRegatta`,`Team_idTeam`),
  KEY `fk_Inscription_Team1_idx` (`Team_idTeam`),
  CONSTRAINT `fk_Inscription_Regatta1` FOREIGN KEY (`Regatta_idRegatta`) REFERENCES `regatta` (`idRegatta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Inscription_Team1` FOREIGN KEY (`Team_idTeam`) REFERENCES `team` (`idTeam`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscription`
--

LOCK TABLES `inscription` WRITE;
/*!40000 ALTER TABLE `inscription` DISABLE KEYS */;
INSERT INTO `inscription` VALUES (1,1),(2,1),(3,1),(1,2),(2,2),(4,2);
/*!40000 ALTER TABLE `inscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regatta`
--

DROP TABLE IF EXISTS `regatta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regatta` (
  `idRegatta` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRegatta`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regatta`
--

LOCK TABLES `regatta` WRITE;
/*!40000 ALTER TABLE `regatta` DISABLE KEYS */;
INSERT INTO `regatta` VALUES (1,'Coupe Du Trimaran'),(2,'Trophée Luc Liardet'),(3,'Coupe des Tas de Pois'),(4,'La Solitaire');
/*!40000 ALTER TABLE `regatta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `idTeam` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` text,
  `Boat_idBoat` int(11) NOT NULL,
  PRIMARY KEY (`idTeam`,`Boat_idBoat`),
  KEY `fk_Team_Boat_idx` (`Boat_idBoat`),
  CONSTRAINT `fk_Team_Boat` FOREIGN KEY (`Boat_idBoat`) REFERENCES `boat` (`idBoat`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,'Jumacabi','',1),(2,'Goldfinger',NULL,2);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teamcomposition`
--

DROP TABLE IF EXISTS `teamcomposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teamcomposition` (
  `Teammate_idTeammate` int(11) NOT NULL,
  `Team_idTeam` int(11) NOT NULL,
  PRIMARY KEY (`Teammate_idTeammate`,`Team_idTeam`),
  KEY `fk_TeamComposition_Teammate1_idx` (`Teammate_idTeammate`),
  KEY `fk_TeamComposition_Team1_idx` (`Team_idTeam`),
  CONSTRAINT `fk_TeamComposition_Team1` FOREIGN KEY (`Team_idTeam`) REFERENCES `team` (`idTeam`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TeamComposition_Teammate1` FOREIGN KEY (`Teammate_idTeammate`) REFERENCES `teammate` (`idTeammate`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teamcomposition`
--

LOCK TABLES `teamcomposition` WRITE;
/*!40000 ALTER TABLE `teamcomposition` DISABLE KEYS */;
INSERT INTO `teamcomposition` VALUES (1,1),(2,1),(3,1),(4,1),(5,2),(6,2),(7,2);
/*!40000 ALTER TABLE `teamcomposition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teammate`
--

DROP TABLE IF EXISTS `teammate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teammate` (
  `idTeammate` int(11) NOT NULL AUTO_INCREMENT,
  `lastname` varchar(45) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`idTeammate`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teammate`
--

LOCK TABLES `teammate` WRITE;
/*!40000 ALTER TABLE `teammate` DISABLE KEYS */;
INSERT INTO `teammate` VALUES (1,'Guillou','Goulven',''),(2,'Hamon','Jean-Baptiste',''),(3,'Petesch','Nicolas',NULL),(4,'Guillermou','Jeremie',NULL),(5,'Le Formal','Marc',NULL),(6,'Cabillic','Erwan',NULL),(7,'Gaveriaux','Marie',NULL);
/*!40000 ALTER TABLE `teammate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-18 17:47:35
