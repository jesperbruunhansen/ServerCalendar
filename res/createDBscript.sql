
CREATE DATABASE cbscalendar;
USE cbscalendar;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table calendar
# ------------------------------------------------------------

DROP TABLE IF EXISTS `calendar`;

CREATE TABLE `calendar` (
  `calendarid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `active` tinyint(4) DEFAULT '1',
  `createdby` varchar(255) NOT NULL,
  `privatepublic` tinyint(4) NOT NULL COMMENT '1 = public 	2 = private',
  PRIMARY KEY (`calendarid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;

INSERT INTO `calendar` (`calendarid`, `name`, `active`, `createdby`, `privatepublic`)
VALUES
	(1,'CBS Calendar',1,'CBS',1),
	(2,'DØK Calendar',1,'DØK Foreningen',1),
	(3,'DØK Fest',0,'DØK \'13',1),
	(4,'Jespers Kalender',1,'Jesper Bruun HAnsen',0),
	(5,'Caspers Kalender',1,'Casper Hansen',0);

/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table dailyupdate
# ------------------------------------------------------------

DROP TABLE IF EXISTS `dailyupdate`;

CREATE TABLE `dailyupdate` (
  `date` datetime NOT NULL,
  `apparentTemperature` double DEFAULT NULL,
  `summary` text,
  `windspeed` double DEFAULT NULL,
  `qotd` varchar(300) NOT NULL,
  PRIMARY KEY (`date`),
  UNIQUE KEY `date` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table events
# ------------------------------------------------------------

DROP TABLE IF EXISTS `events`;

CREATE TABLE `events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` varchar(255) NOT NULL DEFAULT '',
  `event_id` varchar(255) NOT NULL,
  `location` varchar(105) NOT NULL DEFAULT '',
  `createdby` int(11) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `title` varchar(255) NOT NULL,
  `text` text NOT NULL,
  `active` tinyint(4) DEFAULT '1',
  `customevent` tinyint(1) DEFAULT '1' COMMENT '1 = custom  0 = not custom',
  `calendarid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `calendarid` (`calendarid`),
  KEY `createdby` (`createdby`),
  KEY `event_id` (`event_id`),
  CONSTRAINT `events_ibfk_1` FOREIGN KEY (`calendarid`) REFERENCES `calendar` (`calendarid`) ON UPDATE NO ACTION,
  CONSTRAINT `events_ibfk_2` FOREIGN KEY (`createdby`) REFERENCES `users` (`userid`) ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;

INSERT INTO `events` (`id`, `activity_id`, `event_id`, `location`, `createdby`, `start`, `end`, `title`, `text`, `active`, `customevent`, `calendarid`)
VALUES
	(13,'','14173623632','DITLAB',2,'2014-11-15 16:45:00','2014-11-15 16:45:00','Event created from the client','',1,1,1);

/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table forecast
# ------------------------------------------------------------

DROP TABLE IF EXISTS `forecast`;

CREATE TABLE `forecast` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `celsius` varchar(12) DEFAULT NULL,
  `createdon` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table locationdata
# ------------------------------------------------------------

DROP TABLE IF EXISTS `locationdata`;

CREATE TABLE `locationdata` (
  `locationdataid` int(11) NOT NULL AUTO_INCREMENT,
  `longitude` int(11) NOT NULL,
  `latitude` int(11) DEFAULT NULL,
  PRIMARY KEY (`locationdataid`),
  UNIQUE KEY `latitude` (`latitude`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table notes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `notes`;

CREATE TABLE `notes` (
  `noteid` int(11) NOT NULL AUTO_INCREMENT,
  `eventid` varchar(255) NOT NULL DEFAULT '',
  `createdby` int(11) NOT NULL,
  `text` text,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`noteid`),
  KEY `createdby` (`createdby`),
  KEY `event_id` (`eventid`),
  CONSTRAINT `notes_ibfk_1` FOREIGN KEY (`createdby`) REFERENCES `users` (`userid`) ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


# Dump of table roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `type` varchar(200) NOT NULL,
  PRIMARY KEY (`roleid`),
  KEY `userid` (`userid`),
  CONSTRAINT `roles_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`) ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;

INSERT INTO `roles` (`roleid`, `userid`, `type`)
VALUES
	(1,1,'admin'),
	(2,2,'user'),
	(3,3,'user'),
	(4,4,'user');

/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table userevents
# ------------------------------------------------------------

DROP TABLE IF EXISTS `userevents`;

CREATE TABLE `userevents` (
  `userid` int(11) NOT NULL,
  `calendarid` int(11) NOT NULL,
  KEY `calendarid` (`calendarid`),
  KEY `userid` (`userid`),
  CONSTRAINT `userevents_ibfk_1` FOREIGN KEY (`calendarid`) REFERENCES `calendar` (`calendarid`) ON UPDATE NO ACTION,
  CONSTRAINT `userevents_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`) ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `userevents` WRITE;
/*!40000 ALTER TABLE `userevents` DISABLE KEYS */;

INSERT INTO `userevents` (`userid`, `calendarid`)
VALUES
	(1,1),
	(2,1),
	(3,1),
	(4,1),
	(2,2),
	(2,3),
	(2,4),
	(3,2),
	(3,3),
	(3,5);

/*!40000 ALTER TABLE `userevents` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `active` tinyint(4) DEFAULT '1',
  `created` datetime DEFAULT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`userid`, `email`, `active`, `created`, `password`)
VALUES
	(1,'admin@admin.cbs.dk',1,NULL,'admin'),
	(2,'jeha13ad@student.cbs.dk',1,NULL,'lol123'),
	(3,'caha13ag@student.cbs.dk',1,NULL,'abc123'),
	(4,'hefu13ab@student.cbs.dk',1,NULL,'abcd');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
