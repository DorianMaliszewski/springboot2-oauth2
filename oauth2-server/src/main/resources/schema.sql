
DROP DATABASE IF EXISTS test;
CREATE DATABASE test;
use test;

-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.5.5-10.3.10-MariaDB-1:10.3.10+maria~bionic

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



DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `oauth_client_details` WRITE;
-- myapplication:secretPassword
INSERT INTO `oauth_client_details` VALUES ('myapplication','ms/admin','{bcrypt}$2a$10$TE0YJMDm6pYtMnvZiYz24OFIa9/7y42R4k4FM8ckp7lqFCZURc92G','ROLE_ADMIN,ROLE_SUPER_ADMIN,ROLE_USER','authorization_code,password,refresh_token,implicit',NULL,NULL,900,3600,'{}',NULL);
UNLOCK TABLES;

--
-- Table structure for table `permissions_per`
--

DROP TABLE IF EXISTS `permissions_per`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions_per` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions_per`
--

LOCK TABLES `permissions_per` WRITE;
/*!40000 ALTER TABLE `permissions_per` DISABLE KEYS */;

INSERT INTO `permissions_per` VALUES
(1,'CAN_DELETE_USER',NOW(),NOW(),0),
(2,'CAN_CREATE_USER',NOW(),NOW(),0),
(3,'CAN_UPDATE_USER',NOW(),NOW(),0),
(4,'CAN_READ_USER',NOW(),NOW(),0);

/*!40000 ALTER TABLE `permissions_per` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_rol`
--

DROP TABLE IF EXISTS `roles_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_rol` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_rol`
--

LOCK TABLES `roles_rol` WRITE;
/*!40000 ALTER TABLE `roles_rol` DISABLE KEYS */;

INSERT INTO `roles_rol` VALUES
(1,'ROLE_ADMIN',NOW(),NOW(),0),
(2,'ROLE_USER',NOW(),NOW(),0),
(3,'ROLE_SUPER_ADMIN',NOW(),NOW(),0);

/*!40000 ALTER TABLE `roles_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_permissions_rpe`
--

DROP TABLE IF EXISTS `roles_permissions_rpe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_permissions_rpe` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  KEY `FKofjbnmwm0mo1hvqpe7fpe54ps` (`permission_id`),
  KEY `FK9q6sm3tq6pp8gyam3y1hl73ol` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_permissions_rpe`
--

LOCK TABLES `roles_permissions_rpe` WRITE;
/*!40000 ALTER TABLE `roles_permissions_rpe` DISABLE KEYS */;

INSERT INTO `roles_permissions_rpe` VALUES
(1,1),(1,2),(1,3),(1,4), /* ADMIN permission */
(2,4), /* USER permission */
(3,1),(3,2),(3,3),(3,4); /* SUPER_ADMIN permission */

/*!40000 ALTER TABLE `roles_permissions_rpe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_use`
--

DROP TABLE IF EXISTS `users_use`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_use` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `account_non_expired` bit(1) NOT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmyn2q4rw8qbl79mr2nu2pnvre` (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_use`
--

LOCK TABLES `users_use` WRITE;
/*!40000 ALTER TABLE `users_use` DISABLE KEYS */;

INSERT INTO `users_use` VALUES
/* admin admin - superadmin superadmin - user user */
(1,'admin','{bcrypt}$2a$10$oOk1T3cw2VhSeNp1fqtK6ObWNcmXaEcwVCdOb2W/QU69B4nn5mY7S','Administrateur','admin@exemple.com',1,1,1,1,1,NOW(),NOW(),0),
(2,'superadmin','{bcrypt}$2a$10$E31FQR7z.s6zQHgcEZLILOAgYsRG8Mxvp7gdXUCeQs6poG487pQt6','Super Administrateur','superadmin@exemple.com',3,1,1,1,1,NOW(),NOW(),0),
(3,'user','{bcrypt}$2a$10$o.gJQIUH1.DOagjGnp3he.n7nM5QyADyWMLJ9GtyW3cU385cdFu0q','Utisateur','user@exemple.com',2,1,1,1,1,NOW(),NOW(),0);

/*!40000 ALTER TABLE `users_use` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-19 21:57:31
