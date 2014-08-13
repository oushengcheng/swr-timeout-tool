-- MySQL dump 10.13  Distrib 5.5.38, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: timeouttool
-- ------------------------------------------------------
-- Server version	5.5.38-0ubuntu0.14.04.1

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
-- Table structure for table `ContingencyAlteration`
--

DROP TABLE IF EXISTS `ContingencyAlteration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ContingencyAlteration` (
  `id` bigint(20) NOT NULL,
  `affect` varchar(255) DEFAULT NULL,
  `delay` varchar(255) DEFAULT NULL,
  `direction` varchar(255) DEFAULT NULL,
  `freeform` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `serviceGroup_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK95368C2E62893424` (`serviceGroup_id`),
  CONSTRAINT `FK95368C2E62893424` FOREIGN KEY (`serviceGroup_id`) REFERENCES `ServiceGroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ContingencyAlteration`
--

LOCK TABLES `ContingencyAlteration` WRITE;
/*!40000 ALTER TABLE `ContingencyAlteration` DISABLE KEYS */;
INSERT INTO `ContingencyAlteration` (`id`, `affect`, `delay`, `direction`, `freeform`, `version`, `serviceGroup_id`) VALUES (163840,'CANCELLED','','BOTH','',0,32774),(163841,'CANCELLED','','BOTH','',0,32787),(163842,'CANCELLED','','BOTH','',0,32783),(163843,'ALTERED','','BOTH','',0,32785),(163844,'ALTERED','','REVERSE','',0,32803),(163845,'ALTERED','','BOTH','',0,32780),(163846,'ALTERED','','FORWARD','XX:09 departures retimed to XX:20',0,32798),(163847,'CANCELLED','','BOTH','',0,32774),(163848,'CANCELLED','','BOTH','',0,32778),(163849,'CANCELLED','','BOTH','',0,32783),(163850,'CANCELLED','','BOTH','',0,32787),(163851,'ALTERED','','BOTH','',0,32785),(163852,'ALTERED','','BOTH','',0,32781),(163853,'ALTERED','','BOTH','',0,32792),(163854,'ALTERED','','REVERSE','',0,32803),(163855,'ALTERED','','BOTH','',0,32780),(163856,'ALTERED','','FORWARD','XX:09 departures retimed to XX:20',0,32798),(163857,'ALTERED','','BOTH','',0,32786);
/*!40000 ALTER TABLE `ContingencyAlteration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ContingencyAlteration_alterations`
--

DROP TABLE IF EXISTS `ContingencyAlteration_alterations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ContingencyAlteration_alterations` (
  `ContingencyAlteration_id` bigint(20) NOT NULL,
  `alterationType` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`ContingencyAlteration_id`,`position`),
  KEY `FKB1591123B046790` (`ContingencyAlteration_id`),
  CONSTRAINT `FKB1591123B046790` FOREIGN KEY (`ContingencyAlteration_id`) REFERENCES `ContingencyAlteration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ContingencyAlteration_alterations`
--

LOCK TABLES `ContingencyAlteration_alterations` WRITE;
/*!40000 ALTER TABLE `ContingencyAlteration_alterations` DISABLE KEYS */;
INSERT INTO `ContingencyAlteration_alterations` (`ContingencyAlteration_id`, `alterationType`, `location`, `position`) VALUES (163843,'STARTFINISH','Basingstoke',0),(163844,'CALLADD','Clapham Jn',0),(163844,'CALLADD','Woking',1),(163844,'CALLADD','Basingstoke',2),(163845,'CALLADD','Berrylands',0),(163846,'NOTCALL','Farnborough',0),(163846,'NOTCALL','Fleet',1),(163851,'STARTFINISH','Basingstoke',0),(163852,'RUNVIA','Twickenham',0),(163852,'CALLADD','Strawberry Hill',1),(163853,'STARTFINISH','Woking',0),(163854,'CALLADD','Clapham Jn',0),(163854,'CALLADD','Woking',1),(163854,'CALLADD','Basingstoke',2),(163855,'CALLADD','Berrylands',0),(163856,'NOTCALL','Farnborough',0),(163856,'NOTCALL','Fleet',1),(163857,'CALLADD','all stations Woking to Surbiton',0);
/*!40000 ALTER TABLE `ContingencyAlteration_alterations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ContingencyPlan`
--

DROP TABLE IF EXISTS `ContingencyPlan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ContingencyPlan` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ContingencyPlan`
--

LOCK TABLES `ContingencyPlan` WRITE;
/*!40000 ALTER TABLE `ContingencyPlan` DISABLE KEYS */;
INSERT INTO `ContingencyPlan` (`id`, `code`, `title`, `version`) VALUES (131072,'SRP01Y','SRP Yellow 1: Mainline 8tph',0),(131073,'SRP01R','SRP  Red 1: Mainline 12tph',0);
/*!40000 ALTER TABLE `ContingencyPlan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ContingencyPlan_ContingencyAlteration`
--

DROP TABLE IF EXISTS `ContingencyPlan_ContingencyAlteration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ContingencyPlan_ContingencyAlteration` (
  `ContingencyPlan_id` bigint(20) NOT NULL,
  `contingencyAlterations_id` bigint(20) NOT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`ContingencyPlan_id`,`position`),
  UNIQUE KEY `UK_6F60192783915195` (`contingencyAlterations_id`),
  KEY `FK6F601927273EBE59` (`contingencyAlterations_id`),
  KEY `FK6F601927CDEA43D0` (`ContingencyPlan_id`),
  CONSTRAINT `FK6F601927CDEA43D0` FOREIGN KEY (`ContingencyPlan_id`) REFERENCES `ContingencyPlan` (`id`),
  CONSTRAINT `FK6F601927273EBE59` FOREIGN KEY (`contingencyAlterations_id`) REFERENCES `ContingencyAlteration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ContingencyPlan_ContingencyAlteration`
--

LOCK TABLES `ContingencyPlan_ContingencyAlteration` WRITE;
/*!40000 ALTER TABLE `ContingencyPlan_ContingencyAlteration` DISABLE KEYS */;
INSERT INTO `ContingencyPlan_ContingencyAlteration` (`ContingencyPlan_id`, `contingencyAlterations_id`, `position`) VALUES (131072,163840,0),(131072,163841,1),(131072,163842,2),(131072,163843,3),(131072,163844,4),(131072,163845,5),(131072,163846,6),(131073,163847,0),(131073,163848,1),(131073,163849,2),(131073,163850,3),(131073,163851,4),(131073,163852,5),(131073,163853,6),(131073,163854,7),(131073,163855,8),(131073,163856,9),(131073,163857,10);
/*!40000 ALTER TABLE `ContingencyPlan_ContingencyAlteration` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-13 19:23:48
