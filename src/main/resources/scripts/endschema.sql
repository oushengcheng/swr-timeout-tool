CREATE DATABASE  IF NOT EXISTS `timeouttool` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `timeouttool`;
-- MySQL dump 10.13  Distrib 5.5.38, for debian-linux-gnu (i686)
--
-- Host: 127.0.0.1    Database: timeouttool
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
-- Table structure for table `AccountTypeEntity`
--

DROP TABLE IF EXISTS `AccountTypeEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccountTypeEntity` (
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `loginName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK37341DAA61F12E7C` (`id`),
  CONSTRAINT `FK37341DAA61F12E7C` FOREIGN KEY (`id`) REFERENCES `IdentityTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AlterationLocation`
--

DROP TABLE IF EXISTS `AlterationLocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AlterationLocation` (
  `id` bigint(20) NOT NULL,
  `alterationType` varchar(255) NOT NULL,
  `direction` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `serviceGroup_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK337CC99462893424` (`serviceGroup_id`),
  CONSTRAINT `FK337CC99462893424` FOREIGN KEY (`serviceGroup_id`) REFERENCES `ServiceGroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AttributeTypeEntity`
--

DROP TABLE IF EXISTS `AttributeTypeEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AttributeTypeEntity` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  `value` varchar(1024) DEFAULT NULL,
  `owner_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE2994759F54BDA12` (`owner_id`),
  CONSTRAINT `FKE2994759F54BDA12` FOREIGN KEY (`owner_id`) REFERENCES `AttributedTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AttributedTypeEntity`
--

DROP TABLE IF EXISTS `AttributedTypeEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AttributedTypeEntity` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `departuretime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK95368C2E62893424` (`serviceGroup_id`),
  CONSTRAINT `FK95368C2E62893424` FOREIGN KEY (`serviceGroup_id`) REFERENCES `ServiceGroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `GroupTypeEntity`
--

DROP TABLE IF EXISTS `GroupTypeEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GroupTypeEntity` (
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9D7A437CF233B3E6` (`parent_id`),
  KEY `FK9D7A437C61F12E7C` (`id`),
  CONSTRAINT `FK9D7A437C61F12E7C` FOREIGN KEY (`id`) REFERENCES `IdentityTypeEntity` (`id`),
  CONSTRAINT `FK9D7A437CF233B3E6` FOREIGN KEY (`parent_id`) REFERENCES `GroupTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `IdentityTypeEntity`
--

DROP TABLE IF EXISTS `IdentityTypeEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IdentityTypeEntity` (
  `createdDate` datetime DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL,
  `expirationDate` datetime DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `partition_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3D6A3F3BBD2E0751` (`partition_id`),
  KEY `FK3D6A3F3B922A4906` (`id`),
  CONSTRAINT `FK3D6A3F3B922A4906` FOREIGN KEY (`id`) REFERENCES `AttributedTypeEntity` (`id`),
  CONSTRAINT `FK3D6A3F3BBD2E0751` FOREIGN KEY (`partition_id`) REFERENCES `PartitionTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Incident`
--

DROP TABLE IF EXISTS `Incident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Incident` (
  `id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL,
  `description` longtext NOT NULL,
  `lastPublished` datetime DEFAULT NULL,
  `nextReview` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(40) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Incident_AUD`
--

DROP TABLE IF EXISTS `Incident_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Incident_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `description` longtext,
  `lastPublished` datetime DEFAULT NULL,
  `nextReview` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKA9740DC3DF74E053` (`REV`),
  CONSTRAINT `FKA9740DC3DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Incident_ServiceGroupAlteration`
--

DROP TABLE IF EXISTS `Incident_ServiceGroupAlteration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Incident_ServiceGroupAlteration` (
  `Incident_id` bigint(20) NOT NULL,
  `serviceGroupAlterations_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_DD011B166DBBA0F0` (`serviceGroupAlterations_id`),
  KEY `FKDD011B1666A7A843` (`serviceGroupAlterations_id`),
  KEY `FKDD011B16FDFEB044` (`Incident_id`),
  CONSTRAINT `FKDD011B16FDFEB044` FOREIGN KEY (`Incident_id`) REFERENCES `Incident` (`id`),
  CONSTRAINT `FKDD011B1666A7A843` FOREIGN KEY (`serviceGroupAlterations_id`) REFERENCES `ServiceGroupAlteration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Incident_ServiceGroupAlteration_AUD`
--

DROP TABLE IF EXISTS `Incident_ServiceGroupAlteration_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Incident_ServiceGroupAlteration_AUD` (
  `REV` int(11) NOT NULL,
  `Incident_id` bigint(20) NOT NULL,
  `serviceGroupAlterations_id` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`Incident_id`,`serviceGroupAlterations_id`),
  KEY `FKF25DCDE7DF74E053` (`REV`),
  CONSTRAINT `FKF25DCDE7DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PartitionTypeEntity`
--

DROP TABLE IF EXISTS `PartitionTypeEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PartitionTypeEntity` (
  `configurationName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK43972627922A4906` (`id`),
  CONSTRAINT `FK43972627922A4906` FOREIGN KEY (`id`) REFERENCES `AttributedTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PasswordCredentialTypeEntity`
--

DROP TABLE IF EXISTS `PasswordCredentialTypeEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PasswordCredentialTypeEntity` (
  `id` bigint(20) NOT NULL,
  `effectiveDate` datetime DEFAULT NULL,
  `expiryDate` datetime DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  `passwordEncodedHash` varchar(255) DEFAULT NULL,
  `passwordSalt` varchar(255) DEFAULT NULL,
  `owner_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK23B1B6CFF54BDA12` (`owner_id`),
  CONSTRAINT `FK23B1B6CFF54BDA12` FOREIGN KEY (`owner_id`) REFERENCES `AttributedTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `REVINFO`
--

DROP TABLE IF EXISTS `REVINFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REVINFO` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RelationshipIdentityTypeEntity`
--

DROP TABLE IF EXISTS `RelationshipIdentityTypeEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RelationshipIdentityTypeEntity` (
  `identifier` bigint(20) NOT NULL,
  `descriptor` varchar(255) DEFAULT NULL,
  `identityType_id` varchar(255) DEFAULT NULL,
  `owner_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`identifier`),
  KEY `FKCD8D23939EF0DF23` (`identityType_id`),
  KEY `FKCD8D239340652182` (`owner_id`),
  CONSTRAINT `FKCD8D239340652182` FOREIGN KEY (`owner_id`) REFERENCES `RelationshipTypeEntity` (`id`),
  CONSTRAINT `FKCD8D23939EF0DF23` FOREIGN KEY (`identityType_id`) REFERENCES `IdentityTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RelationshipTypeEntity`
--

DROP TABLE IF EXISTS `RelationshipTypeEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RelationshipTypeEntity` (
  `typeName` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF8E62435922A4906` (`id`),
  CONSTRAINT `FKF8E62435922A4906` FOREIGN KEY (`id`) REFERENCES `AttributedTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RoleTypeEntity`
--

DROP TABLE IF EXISTS `RoleTypeEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RoleTypeEntity` (
  `name` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCAD418F361F12E7C` (`id`),
  CONSTRAINT `FKCAD418F361F12E7C` FOREIGN KEY (`id`) REFERENCES `IdentityTypeEntity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ServiceGroup`
--

DROP TABLE IF EXISTS `ServiceGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceGroup` (
  `id` bigint(20) NOT NULL,
  `bothDescription` varchar(255) DEFAULT NULL,
  `forwardDescription` varchar(255) DEFAULT NULL,
  `headcode` varchar(255) DEFAULT NULL,
  `orderingIndex` bigint(20) DEFAULT NULL,
  `reverseDescription` varchar(255) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `unidirectional` tinyint(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `serviceGrouping_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `_UniqueKey` (`uid`),
  KEY `FK1DEE7CEACDE5FBD0` (`serviceGrouping_id`),
  CONSTRAINT `FK1DEE7CEACDE5FBD0` FOREIGN KEY (`serviceGrouping_id`) REFERENCES `ServiceGrouping` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ServiceGroupAlteration`
--

DROP TABLE IF EXISTS `ServiceGroupAlteration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceGroupAlteration` (
  `id` bigint(20) NOT NULL,
  `affect` varchar(255) NOT NULL,
  `delay` varchar(50) DEFAULT NULL,
  `direction` varchar(255) NOT NULL,
  `effectiveFrom` varchar(100) DEFAULT NULL,
  `freeform` varchar(100) DEFAULT NULL,
  `position` int(11) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `incident_id` bigint(20) DEFAULT NULL,
  `serviceGroup_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3886D529FDFEB044` (`incident_id`),
  KEY `FK3886D52962893424` (`serviceGroup_id`),
  CONSTRAINT `FK3886D52962893424` FOREIGN KEY (`serviceGroup_id`) REFERENCES `ServiceGroup` (`id`),
  CONSTRAINT `FK3886D529FDFEB044` FOREIGN KEY (`incident_id`) REFERENCES `Incident` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ServiceGroupAlteration_AUD`
--

DROP TABLE IF EXISTS `ServiceGroupAlteration_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceGroupAlteration_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `affect` varchar(255) DEFAULT NULL,
  `delay` varchar(50) DEFAULT NULL,
  `direction` varchar(255) DEFAULT NULL,
  `effectiveFrom` varchar(100) DEFAULT NULL,
  `freeform` varchar(100) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `incident_id` bigint(20) DEFAULT NULL,
  `serviceGroup_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK4182467ADF74E053` (`REV`),
  CONSTRAINT `FK4182467ADF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ServiceGroupAlteration_alterations`
--

DROP TABLE IF EXISTS `ServiceGroupAlteration_alterations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceGroupAlteration_alterations` (
  `ServiceGroupAlteration_id` bigint(20) NOT NULL,
  `alterationType` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`ServiceGroupAlteration_id`,`position`),
  KEY `FK90F4B99EF433DB64` (`ServiceGroupAlteration_id`),
  CONSTRAINT `FK90F4B99EF433DB64` FOREIGN KEY (`ServiceGroupAlteration_id`) REFERENCES `ServiceGroupAlteration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ServiceGroupAlteration_alterations_AUD`
--

DROP TABLE IF EXISTS `ServiceGroupAlteration_alterations_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceGroupAlteration_alterations_AUD` (
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) NOT NULL,
  `ServiceGroupAlteration_id` bigint(20) NOT NULL,
  `position` int(11) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `alterationType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`REV`,`REVTYPE`,`ServiceGroupAlteration_id`,`position`),
  KEY `FK2ECEE86FDF74E053` (`REV`),
  CONSTRAINT `FK2ECEE86FDF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ServiceGroup_AUD`
--

DROP TABLE IF EXISTS `ServiceGroup_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceGroup_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `bothDescription` varchar(255) DEFAULT NULL,
  `forwardDescription` varchar(255) DEFAULT NULL,
  `headcode` varchar(255) DEFAULT NULL,
  `orderingIndex` bigint(20) DEFAULT NULL,
  `reverseDescription` varchar(255) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `unidirectional` tinyint(1) DEFAULT NULL,
  `serviceGrouping_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK575E25BBDF74E053` (`REV`),
  CONSTRAINT `FK575E25BBDF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ServiceGrouping`
--

DROP TABLE IF EXISTS `ServiceGrouping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceGrouping` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ServiceGrouping_AUD`
--

DROP TABLE IF EXISTS `ServiceGrouping_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceGrouping_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK73DC0C89DF74E053` (`REV`),
  CONSTRAINT `FK73DC0C89DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_unique_key`
--

DROP TABLE IF EXISTS `hibernate_unique_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_unique_key` (
  `next_hi` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `schema_version`
--

DROP TABLE IF EXISTS `schema_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schema_version` (
  `version_rank` int(11) NOT NULL,
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`version`),
  KEY `schema_version_vr_idx` (`version_rank`),
  KEY `schema_version_ir_idx` (`installed_rank`),
  KEY `schema_version_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-10 11:58:31
