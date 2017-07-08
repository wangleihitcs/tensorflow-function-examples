-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: treasory
-- ------------------------------------------------------
-- Server version	5.7.9-log


--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `bill_id` varchar(20) not NULL
    primary key,
  `date` date DEFAULT NULL,
  `money` double DEFAULT NULL,
  `expalin` text,
  `type` char(20) DEFAULT NULL,
  `username` char(20) DEFAULT NULL
);




